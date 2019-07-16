package utils

import akka.util.ByteString
import com.fullfacing.backend.AkkaMonixHttpBackend
import com.fullfacing.keycloak4s.admin.monix.client.{Keycloak, KeycloakClient}
import com.fullfacing.keycloak4s.admin.monix.services.{ClientScopes, _}
import com.fullfacing.keycloak4s.core.models.KeycloakConfig
import com.softwaremill.sttp.SttpBackend
import monix.eval.Task
import monix.execution.Scheduler
import monix.reactive.Observable
import org.scalatest._

import scala.concurrent.Future

class IntegrationSpec extends AsyncFlatSpec with Matchers with Inspectors {

  /* Keycloak Server Configuration **/
  val authConfig      = KeycloakConfig.Auth("master", "admin-cli", ServerInitializer.clientSecret)
  val keycloakConfig  = KeycloakConfig("http", "127.0.0.1", 8080, "master", authConfig)

  /* Keycloak Client Implicits **/
  implicit val context: Scheduler = monix.execution.Scheduler.global
  implicit val backend: SttpBackend[Task, Observable[ByteString]] = AkkaMonixHttpBackend()
  implicit val client: KeycloakClient = new KeycloakClient(keycloakConfig)

  /* Keycloak Services **/
  val attackDetService: AttackDetection   = Keycloak.AttackDetection
  val authMgmt: AuthenticationManagement  = Keycloak.AuthenticationManagement
  val clientScopeService: ClientScopes    = Keycloak.ClientScopes
  val clientService: Clients              = Keycloak.Clients
  val componentService: Components        = Keycloak.Components
  val groupService: Groups                = Keycloak.Groups
  val idProvService: IdentityProviders    = Keycloak.IdentityProviders
  val protocolMapService: ProtocolMappers = Keycloak.ProtocolMappers
  val realmService: RealmsAdmin           = Keycloak.RealmsAdmin
  val rolesByIdService: RolesById         = Keycloak.RolesById
  val roleService: Roles                  = Keycloak.Roles
  val userService: Users                  = Keycloak.Users

  /* Sub-Services **/
  val clientRoleService: roleService.ClientLevel.type = roleService.ClientLevel
  val realmRoleService: roleService.RealmLevel.type   = roleService.RealmLevel

  /* Implicit Helper Classes **/
  implicit class taskImpl[A, B](task: Task[Either[B, A]]) {
    def shouldReturnSuccess: Future[Assertion] = task.map { response =>
      response shouldBe a [scala.util.Right[_, _]]
    }.runToFuture
  }
}