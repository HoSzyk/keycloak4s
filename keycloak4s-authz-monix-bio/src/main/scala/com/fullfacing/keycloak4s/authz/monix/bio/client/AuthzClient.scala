package com.fullfacing.keycloak4s.authz.monix.bio.client

import cats.implicits._
import com.fullfacing.keycloak4s.admin.implicits.{Anything, BodyMagnet}
import com.fullfacing.keycloak4s.admin.utils.Client._
import com.fullfacing.keycloak4s.authz.monix.bio.Logging
import com.fullfacing.keycloak4s.authz.monix.bio.client.AuthzClient._
import com.fullfacing.keycloak4s.authz.monix.bio.models._
import com.fullfacing.keycloak4s.authz.monix.bio.resources.{AuthorizationResource, PermissionResource, PolicyResource, ProtectedResource}
import com.fullfacing.keycloak4s.core.models._
import com.fullfacing.keycloak4s.core.serialization.JsonFormats.default
import monix.bio.{IO, Task, UIO}
import org.json4s.jackson.Serialization.read
import sttp.client.{Identity, NothingT, RequestT, Response, SttpBackend, UriContext, asString, basicRequest}
import sttp.model.Uri.QuerySegment.KeyValue
import sttp.model.{StatusCode, Uri}

import java.util.UUID
import scala.annotation.tailrec
import scala.collection.immutable.Seq
import scala.reflect.Manifest
import scala.reflect.runtime.universe._

final class AuthzClient[S](config: ConfigWithAuth, private[bio] val serverConfig: ServerConfiguration)
                          (implicit client: SttpBackend[IO[Throwable, *], S, NothingT])
  extends TokenManager[S](config, serverConfig) {

  val realm: String = config.realm

  /* Extracts the Manifest out of an implicit Anything. **/
  private implicit def ma[A : Anything]: Manifest[A] = implicitly[Anything[A]].manifest

  /* HTTP Call Builders **/
  private def asResponseObject[A <: Any : Manifest](implicit tag: TypeTag[A], cId: UUID) = {
    asString.mapWithMetadata { case (raw, meta) =>
      raw.map { body =>
        Logging.requestSuccessful(body, cId)

        if (tag.tpe =:= typeOf[Unit]) read[A]("null")
        else if (tag.tpe =:= typeOf[Headers]) meta.headers.map(h => h.name -> h.value).toMap.asInstanceOf[A]
        else read[A](body)
      }
    }
  }

  private def call[B <: Any : Manifest](requestT: RequestT[Identity, Either[String, String], Nothing],
                                        requestInfo: RequestInfo): IO[KeycloakError, B] = {
    implicit val cId: UUID = UUID.randomUUID()

    val request = requestT
      .header("Accept", "application/json")
      .response(asResponseObject[B])

    (for {
      req      <- withAuth(request)
      _        <- UIO(Logging.requestSent(requestInfo, cId))
      response <- req.send()
      b        <- liftM(response, requestInfo)
    } yield b)
      .onErrorRecoverWith {
        case KeycloakSttpException(StatusCode.Unauthorized.code, _, _, _, _) =>
          for {
            token <- withAuthNewToken(request)
            _     <- UIO(Logging.retryUnauthorized(requestInfo, cId))
            retry <- token.send()
            b     <- liftM(retry, requestInfo)
          } yield b
      }
      .mapError { ex =>
        Logging.requestFailed(cId, ex)
        KeycloakThrowable(ex)
      }
  }

  /* REST Protocol Calls **/
  def get[A : Anything](uri: Uri, query: Seq[KeyValue] = Seq.empty[KeyValue]): IO[KeycloakError, A] = {
    val request = basicRequest.get(addQuerySegments(uri, query))
    call[A](request, buildRequestInfo(uri, "GET", ()))
  }

  def put[A : Anything](uri: Uri, payload: BodyMagnet = (), query: Seq[KeyValue] = Seq.empty[KeyValue]): IO[KeycloakError, A] = {
    val request   = basicRequest.put(addQuerySegments(uri, query))
    val injected  = payload.apply(request)
    call[A](injected, buildRequestInfo(uri, "PUT", injected.body))
  }

  def post[A : Anything](uri: Uri, payload: BodyMagnet = (), query: Seq[KeyValue] = Seq.empty[KeyValue]): IO[KeycloakError, A] = {
    val request   = basicRequest.post(addQuerySegments(uri, query))
    val injected  = payload.apply(request)
    call[A](injected, buildRequestInfo(uri, "POST", injected.body))
  }

  def delete[A : Anything](uri: Uri, payload: BodyMagnet = (), query: Seq[KeyValue] = Seq.empty[KeyValue]): IO[KeycloakError, A] = {
    val request   = basicRequest.delete(addQuerySegments(uri, query))
    val injected  = payload.apply(request)
    call[A](injected, buildRequestInfo(uri, "DELETE", injected.body))
  }

  def authorization(): AuthorizationResource[S] = new AuthorizationResource[S](config, serverConfig)
  def policy(): PolicyResource[S]               = new PolicyResource[S]()(this)
  def permission(): PermissionResource[S]       = new PermissionResource[S]()(this)
  def resource(): ProtectedResource[S]          = new ProtectedResource[S]()(this)
}

object AuthzClient {

  type Headers = Map[String, String]
  val EMPTY_HEADERS: Headers = Map.empty[String, String]

  lazy val CONFIGURATION_PATH: String = "/.well-known/uma2-configuration"

  @tailrec
  def addQuerySegments(uri: Uri, query: Seq[KeyValue]): Uri = query match {
    case Nil    => uri
    case h :: t => addQuerySegments(uri.querySegment(h), t)
  }

  def liftM[A](response: Response[Either[String, A]], requestInfo: RequestInfo): IO[KeycloakError, A] = {
    IO.fromEither(response.body).mapError(l => buildError(response, l, requestInfo))
  }

  private def retrieveServerConfiguration(config: ConfigWithAuth)
                                         (implicit client: SttpBackend[Task[*], _, NothingT]): IO[KeycloakError, ServerConfiguration] = {
    basicRequest
      .get(uri"${config.buildBaseUri}/realms/${config.realm}/.well-known/uma2-configuration")
      .response(asString)
      .send()
      .mapError(KeycloakThrowable)
      .map { response =>
        response.body.bimap(
          error => KeycloakException(response.code.code, response.statusText, Some(error)),
          res   => read[ServerConfiguration](res)
        )
      }
      .flatMap(IO.fromEither)
  }

  def initialise[S](config: ConfigWithAuth)
                   (implicit client: SttpBackend[Task[*], S, NothingT]): IO[KeycloakError, AuthzClient[S]] = {
    retrieveServerConfiguration(config)
      .map(AuthzClient(config, _))
  }

  def apply[S](config: ConfigWithAuth, serverConfig: ServerConfiguration)
              (implicit client: SttpBackend[Task[*], S, NothingT]): AuthzClient[S] = {
    new AuthzClient[S](config, serverConfig)
  }
}