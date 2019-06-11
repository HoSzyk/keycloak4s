package com.fullfacing.keycloak4s.auth.akka.http.directives

import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.StandardRoute._
import akka.http.scaladsl.server._
import akka.http.scaladsl.server.util.Tuple._
import com.fullfacing.keycloak4s.auth.akka.http.PayloadImplicits._
import com.fullfacing.keycloak4s.auth.akka.http.directives.magnets.{AuthoriseResourceMagnet, PathPrefixWithAuthMagnet, PathWithAuthMagnet, WithAuthMagnet}
import com.fullfacing.keycloak4s.auth.akka.http.models.{AuthPayload, AuthRoles}
import com.fullfacing.keycloak4s.core.Exceptions.UNAUTHORIZED
import com.fullfacing.keycloak4s.core.serialization.JsonFormats._
import org.json4s.jackson.Serialization.write

trait AuthorisationDirectives {

  /**
   * HTTP methods with checks to ensure the user has the permission to perform the attempted
   * operation on the already authorised resource
   */
  def getA(p: AuthRoles): Directive0    = get.tflatMap(_ => authoriseMethod(p))
  def putA(p: AuthRoles): Directive0    = put.tflatMap(_ => authoriseMethod(p))
  def headA(p: AuthRoles): Directive0   = head.tflatMap(_ => authoriseMethod(p))
  def postA(p: AuthRoles): Directive0   = post.tflatMap(_ => authoriseMethod(p))
  def patchA(p: AuthRoles): Directive0  = patch.tflatMap(_ => authoriseMethod(p))
  def deleteA(p: AuthRoles): Directive0 = delete.tflatMap(_ => authoriseMethod(p))

  /** Authorises both the resource and operation */
  def withAuth(magnet: WithAuthMagnet): magnet.Result = magnet()

  /**
   * Uses the service specific permissions from the user's validated token to determine the user's access to this resource.
   *
   * @return The allowed operations available to the user on this resource
   */
  def authoriseResource(magnet: AuthoriseResourceMagnet): magnet.Result = magnet()

  /** Authorises the operation based on the HTTP method and the authorised roles the user has on the resource */
  def authoriseMethod(resource: AuthRoles): Directive0 = {
    extractMethod.flatMap { method =>
      authorize {
        resource.roles.intersect(AuthorisationDirectives.scopeMap(method)).nonEmpty
      }
    }
  }

  /**
   * Path directive that authorises access to the path resource.
   * Example usage:
   *  pathA("resource") { id => ??? }
   *  pathA((JavaUUID, "resource")) { (roles, id) => ??? }
   *  pathA(("resource", JavaUUID)) { (roles, id) => ??? }
   */
  def pathA[A](magnet: PathWithAuthMagnet[A]): magnet.Result = magnet()
  def pathPrefixA[A](magnet: PathPrefixWithAuthMagnet[A]): magnet.Result = magnet()
}

object AuthorisationDirectives {

  private implicit def routeToDirective[A](route: StandardRoute): Directive[A] = {
    toDirective[A](route)(yes[A])
  }

  def scopeMap(method: HttpMethod): List[String] = method match {
    case HttpMethods.GET    | HttpMethods.HEAD                     => List("delete", "create", "view")
    case HttpMethods.POST   | HttpMethods.PUT  | HttpMethods.PATCH => List("delete", "create")
    case HttpMethods.DELETE                                        => List("delete")
    case _                                                         => List.empty[String]
  }

  /**
   * Looks for the requested resource in the user's permissions from the validated access token.
   * The request is rejected if not found.
   *
   * @param resource     The resource the user is attempting to access.
   * @param permissions  The resources and methods allowed for the user.
   * @param success      A directive to create if the user has access to the resource.
   * @return             The resulting directive from the auth result and the function provided.
   */
  def checkPermissions[A](resource: String, permissions: AuthPayload, success: AuthRoles => Directive[A]): Directive[A] = {
    permissions.accessToken.extractResources.find { case (k, _) => k.equalsIgnoreCase(resource) } match {
      case Some((_, v)) => success(v)
      case None         => authorisationFailed()
    }
  }

  def authorisationFailed(): StandardRoute =
    complete(HttpResponse(UNAUTHORIZED.code, entity = HttpEntity(ContentTypes.`application/json`, write(UNAUTHORIZED))))
}