package com.fullfacing.keycloak4s.core.models

import com.fullfacing.keycloak4s.core.models.enums.EventType

final case class RealmRepresentation(accessCodeLifespan: Int,
                                     accessCodeLifespanLogin: Int,
                                     accessCodeLifespanUserAction: Int,
                                     accessTokenLifespan: Int,
                                     accessTokenLifespanForImplicitFlow: Int,
                                     accountTheme: Option[String],
                                     actionTokenGeneratedByAdminLifespan: Int,
                                     actionTokenGeneratedByUserLifespan: Int,
                                     adminEventsDetailsEnabled: Boolean,
                                     adminEventsEnabled: Boolean,
                                     adminTheme: Option[String],
                                     attributes: Map[String, String],
                                     authenticationFlows: Option[AuthenticationFlow],
                                     authenticatorConfig: Option[AuthenticatorConfig],
                                     browserFlow: String,
                                     browserSecurityHeaders: Map[String, String],
                                     bruteForceProtected: Boolean,
                                     clientAuthenticationFlow: String,
                                     clientScopeMappings: Map[String, Any] = Map.empty[String, Any],
                                     clientScopes: List[ClientScope],
                                     clients: List[Client],
                                     components: Option[MultivaluedHashMap],
                                     defaultDefaultClientScopes: List[String],
                                     defaultGroups: List[String],
                                     defaultLocale: Option[String],
                                     defaultOptionalClientScopes: List[String],
                                     defaultRoles: List[String],
                                     defaultSignatureAlgorithm: Option[String],
                                     directGrantFlow: String,
                                     displayName: Option[String],
                                     displayNameHtml: Option[String],
                                     dockerAuthenticationFlow: String,
                                     duplicateEmailsAllowed: Boolean,
                                     editUsernameAllowed: Boolean,
                                     emailTheme: Option[String],
                                     enabled: Boolean,
                                     enabledEventTypes: List[EventType],
                                     eventsEnabled: Boolean,
                                     eventsExpiration: Option[Long],
                                     eventsListeners: List[String],
                                     failureFactor: Int,
                                     federatedUsers: List[User],
                                     groups: List[Group],
                                     id: String,
                                     identityProviderMappers: List[IdentityProviderMapper],
                                     identityProviders: List[IdentityProvider],
                                     internationalizationEnabled: Boolean,
                                     keycloakVersion: Option[String],
                                     loginTheme: Option[String],
                                     loginWithEmailAllowed: Boolean,
                                     maxDeltaTimeSeconds: Int,
                                     maxFailureWaitSeconds: Int,
                                     minimumQuickLoginWaitSeconds: Int,
                                     notBefore: Int,
                                     offlineSessionIdleTimeout: Int,
                                     offlineSessionMaxLifespan: Int,
                                     offlineSessionMaxLifespanEnabled: Boolean,
                                     otpPolicyAlgorithm: String,
                                     otpPolicyDigits: Int,
                                     otpPolicyInitialCounter: Int,
                                     otpPolicyLookAheadWindow: Int,
                                     otpPolicyPeriod: Int,
                                     otpPolicyType: String,
                                     passwordPolicy: Option[String],
                                     permanentLockout: Boolean,
                                     protocolMappers: List[ProtocolMapper],
                                     quickLoginCheckMilliSeconds: Long,
                                     realm: String,
                                     refreshTokenMaxReuse: Int,
                                     registrationAllowed: Boolean,
                                     registrationEmailAsUsername: Boolean,
                                     registrationFlow: String,
                                     rememberMe: Boolean,
                                     requiredActions: Option[RequiredActionProvider],
                                     resetCredentialsFlow: String,
                                     resetPasswordAllowed: Boolean,
                                     revokeRefreshToken: Boolean,
                                     roles: Option[RolesRepresentation],
                                     scopeMappings: List[ScopeMapping],
                                     smtpServer: Map[String, Any],
                                     sslRequired: String,
                                     ssoSessionIdleTimeout: Int,
                                     ssoSessionIdleTimeoutRememberMe: Int,
                                     ssoSessionMaxLifespan: Int,
                                     ssoSessionMaxLifespanRememberMe: Int,
                                     supportedLocales: List[String],
                                     userFederationMappers: List[UserFederationMapper],
                                     userFederationProviders: List[UserFederationProvider],
                                     userManagedAccessAllowed: Boolean,
                                     users: Option[User],
                                     verifyEmail: Boolean,
                                     waitIncrementSeconds: Int)

object RealmRepresentation {

  final case class Create(realm: String,
                          id: String,
                          accessCodeLifespan: Option[Int] = None,
                          accessCodeLifespanLogin: Option[Int] = None,
                          accessCodeLifespanUserAction: Option[Int] = None,
                          accessTokenLifespan: Option[Int] = None,
                          accessTokenLifespanForImplicitFlow: Option[Int] = None,
                          accountTheme: Option[String] = None,
                          actionTokenGeneratedByAdminLifespan: Option[Int] = None,
                          actionTokenGeneratedByUserLifespan: Option[Int] = None,
                          adminEventsDetailsEnabled: Option[Boolean] = None,
                          adminEventsEnabled: Option[Boolean] = None,
                          adminTheme: Option[String] = None,
                          attributes: Option[Map[String, String]] = None,
                          authenticationFlows: Option[AuthenticationFlow] = None,
                          authenticatorConfig: Option[AuthenticatorConfig] = None,
                          browserFlow: Option[String] = None,
                          browserSecurityHeaders: Option[Map[String, String]] = None,
                          bruteForceProtected: Option[Boolean] = None,
                          clientAuthenticationFlow: Option[String] = None,
                          clientScopeMappings: Option[Map[String, Any]] = None,
                          clientScopes: Option[List[ClientScope]] = None,
                          clients: Option[List[Client]] = None,
                          components: Option[MultivaluedHashMap] = None,
                          defaultDefaultClientScopes: Option[List[String]] = None,
                          defaultGroups: Option[List[String]] = None,
                          defaultLocale: Option[String] = None,
                          defaultOptionalClientScopes: Option[List[String]] = None,
                          defaultRoles: Option[List[String]] = None,
                          defaultSignatureAlgorithm: Option[String] = None,
                          directGrantFlow: Option[String] = None,
                          displayName: Option[String] = None,
                          displayNameHtml: Option[String] = None,
                          dockerAuthenticationFlow: Option[String] = None,
                          duplicateEmailsAllowed: Option[Boolean] = None,
                          editUsernameAllowed: Option[Boolean] = None,
                          emailTheme: Option[String] = None,
                          enabled: Option[Boolean] = None,
                          enabledEventTypes: Option[List[EventType]] = None,
                          eventsEnabled: Option[Boolean] = None,
                          eventsExpiration: Option[Long] = None,
                          eventsListeners: Option[List[String]] = None,
                          failureFactor: Option[Int] = None,
                          federatedUsers: Option[List[User]] = None,
                          groups:Option[List[Group]] = None,
                          identityProviderMappers: Option[List[IdentityProviderMapper]] = None,
                          identityProviders: Option[List[IdentityProvider]] = None,
                          internationalizationEnabled: Option[Boolean] = None,
                          keycloakVersion: Option[String] = None,
                          loginTheme: Option[String] = None,
                          loginWithEmailAllowed: Option[Boolean] = None,
                          maxDeltaTimeSeconds: Option[Int] = None,
                          maxFailureWaitSeconds: Option[Int] = None,
                          minimumQuickLoginWaitSeconds: Option[Int] = None,
                          notBefore: Option[Int] = None,
                          offlineSessionIdleTimeout: Option[Int] = None,
                          offlineSessionMaxLifespan: Option[Int] = None,
                          offlineSessionMaxLifespanEnabled: Option[Boolean] = None,
                          otpPolicyAlgorithm: Option[String] = None,
                          otpPolicyDigits: Option[Int] = None,
                          otpPolicyInitialCounter: Option[Int] = None,
                          otpPolicyLookAheadWindow: Option[Int] = None,
                          otpPolicyPeriod: Option[Int] = None,
                          otpPolicyType: Option[String] = None,
                          passwordPolicy: Option[String] = None,
                          permanentLockout: Option[Boolean] = None,
                          protocolMappers: Option[List[ProtocolMapper]] = None,
                          quickLoginCheckMilliSeconds: Option[Long] = None,
                          refreshTokenMaxReuse: Option[Int] = None,
                          registrationAllowed: Option[Boolean] = None,
                          registrationEmailAsUsername: Option[Boolean] = None,
                          registrationFlow: Option[String] = None,
                          rememberMe: Option[Boolean] = None,
                          requiredActions: Option[RequiredActionProvider] = None,
                          resetCredentialsFlow: Option[String] = None,
                          resetPasswordAllowed: Option[Boolean] = None,
                          revokeRefreshToken: Option[Boolean] = None,
                          roles: Option[RolesRepresentation] = None,
                          scopeMappings: Option[List[ScopeMapping]] = None,
                          smtpServer: Option[Map[String, Any]] = None,
                          sslRequired: Option[String] = None,
                          ssoSessionIdleTimeout: Option[Int] = None,
                          ssoSessionIdleTimeoutRememberMe: Option[Int] = None,
                          ssoSessionMaxLifespan: Option[Int] = None,
                          ssoSessionMaxLifespanRememberMe: Option[Int] = None,
                          supportedLocales: Option[List[String]] = None,
                          userFederationMappers: Option[List[UserFederationMapper]] = None,
                          userFederationProviders: Option[List[UserFederationProvider]] = None,
                          userManagedAccessAllowed: Option[Boolean] = None,
                          users: Option[User] = None,
                          verifyEmail: Option[Boolean] = None,
                          waitIncrementSeconds: Option[Int] = None)

  final case class Update(realm: Option[String] = None,
                          accessCodeLifespan: Option[Int] = None,
                          accessCodeLifespanLogin: Option[Int] = None,
                          accessCodeLifespanUserAction: Option[Int] = None,
                          accessTokenLifespan: Option[Int] = None,
                          accessTokenLifespanForImplicitFlow: Option[Int] = None,
                          accountTheme: Option[String] = None,
                          actionTokenGeneratedByAdminLifespan: Option[Int] = None,
                          actionTokenGeneratedByUserLifespan: Option[Int] = None,
                          adminEventsDetailsEnabled: Option[Boolean] = None,
                          adminEventsEnabled: Option[Boolean] = None,
                          adminTheme: Option[String] = None,
                          attributes: Option[Map[String, String]] = None,
                          authenticationFlows: Option[AuthenticationFlow] = None,
                          authenticatorConfig: Option[AuthenticatorConfig] = None,
                          browserFlow: Option[String] = None,
                          browserSecurityHeaders: Option[Map[String, String]] = None,
                          bruteForceProtected: Option[Boolean] = None,
                          clientAuthenticationFlow: Option[String] = None,
                          clientScopeMappings: Option[Map[String, Any]] = None,
                          clientScopes: Option[List[ClientScope]] = None,
                          clients: Option[List[Client]] = None,
                          components: Option[MultivaluedHashMap] = None,
                          defaultDefaultClientScopes: Option[List[String]] = None,
                          defaultGroups: Option[List[String]] = None,
                          defaultLocale: Option[String] = None,
                          defaultOptionalClientScopes: Option[List[String]] = None,
                          defaultRoles: Option[List[String]] = None,
                          defaultSignatureAlgorithm: Option[String] = None,
                          directGrantFlow: Option[String] = None,
                          displayName: Option[String] = None,
                          displayNameHtml: Option[String] = None,
                          dockerAuthenticationFlow: Option[String] = None,
                          duplicateEmailsAllowed: Option[Boolean] = None,
                          editUsernameAllowed: Option[Boolean] = None,
                          emailTheme: Option[String] = None,
                          enabled: Option[Boolean] = None,
                          enabledEventTypes: Option[List[EventType]] = None,
                          eventsEnabled: Option[Boolean] = None,
                          eventsExpiration: Option[Long] = None,
                          eventsListeners: Option[List[String]] = None,
                          failureFactor: Option[Int] = None,
                          federatedUsers: Option[List[User]] = None,
                          groups:Option[List[Group]] = None,
                          identityProviderMappers: Option[List[IdentityProviderMapper]] = None,
                          identityProviders: Option[List[IdentityProvider]] = None,
                          internationalizationEnabled: Option[Boolean] = None,
                          keycloakVersion: Option[String] = None,
                          loginTheme: Option[String] = None,
                          loginWithEmailAllowed: Option[Boolean] = None,
                          maxDeltaTimeSeconds: Option[Int] = None,
                          maxFailureWaitSeconds: Option[Int] = None,
                          minimumQuickLoginWaitSeconds: Option[Int] = None,
                          notBefore: Option[Int] = None,
                          offlineSessionIdleTimeout: Option[Int] = None,
                          offlineSessionMaxLifespan: Option[Int] = None,
                          offlineSessionMaxLifespanEnabled: Option[Boolean] = None,
                          otpPolicyAlgorithm: Option[String] = None,
                          otpPolicyDigits: Option[Int] = None,
                          otpPolicyInitialCounter: Option[Int] = None,
                          otpPolicyLookAheadWindow: Option[Int] = None,
                          otpPolicyPeriod: Option[Int] = None,
                          otpPolicyType: Option[String] = None,
                          passwordPolicy: Option[String] = None,
                          permanentLockout: Option[Boolean] = None,
                          protocolMappers: Option[List[ProtocolMapper]] = None,
                          quickLoginCheckMilliSeconds: Option[Long] = None,
                          refreshTokenMaxReuse: Option[Int] = None,
                          registrationAllowed: Option[Boolean] = None,
                          registrationEmailAsUsername: Option[Boolean] = None,
                          registrationFlow: Option[String] = None,
                          rememberMe: Option[Boolean] = None,
                          requiredActions: Option[RequiredActionProvider] = None,
                          resetCredentialsFlow: Option[String] = None,
                          resetPasswordAllowed: Option[Boolean] = None,
                          revokeRefreshToken: Option[Boolean] = None,
                          roles: Option[RolesRepresentation] = None,
                          scopeMappings: Option[List[ScopeMapping]] = None,
                          smtpServer: Option[Map[String, Any]] = None,
                          sslRequired: Option[String] = None,
                          ssoSessionIdleTimeout: Option[Int] = None,
                          ssoSessionIdleTimeoutRememberMe: Option[Int] = None,
                          ssoSessionMaxLifespan: Option[Int] = None,
                          ssoSessionMaxLifespanRememberMe: Option[Int] = None,
                          supportedLocales: Option[List[String]] = None,
                          userFederationMappers: Option[List[UserFederationMapper]] = None,
                          userFederationProviders: Option[List[UserFederationProvider]] = None,
                          userManagedAccessAllowed: Option[Boolean] = None,
                          users: Option[User] = None,
                          verifyEmail: Option[Boolean] = None,
                          waitIncrementSeconds: Option[Int] = None)
}