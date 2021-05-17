package com.fullfacing.keycloak4s.core.models.enums

import enumeratum.values.{StringEnum, StringEnumEntry}

import scala.collection.immutable

sealed abstract class EventType(val value: String) extends StringEnumEntry

case object EventTypes extends StringEnum[EventType] {
  case object AccountDelete                       extends EventType("DELETE_ACCOUNT")
  case object AccountDeleteError                  extends EventType("DELETE_ACCOUNT_ERROR")
  case object AuthRequestIdToToken                extends EventType("AUTHREQID_TO_TOKEN")
  case object AuthRequestIdToTokenError           extends EventType("AUTHREQID_TO_TOKEN_ERROR")
  case object ClientDelete                        extends EventType("CLIENT_DELETE")
  case object ClientDeleteError                   extends EventType("CLIENT_DELETE_ERROR")
  case object ClientInfo                          extends EventType("CLIENT_INFO")
  case object ClientInfoError                     extends EventType("CLIENT_INFO_ERROR")
  case object ClientInitiatedAccountLinking       extends EventType("CLIENT_INITIATED_ACCOUNT_LINKING")
  case object ClientInitiatedAccountLinkingError  extends EventType("CLIENT_INITIATED_ACCOUNT_LINKING_ERROR")
  case object ClientLogin                         extends EventType("CLIENT_LOGIN")
  case object ClientLoginError                    extends EventType("CLIENT_LOGIN_ERROR")
  case object ClientRegister                      extends EventType("CLIENT_REGISTER")
  case object ClientRegisterError                 extends EventType("CLIENT_REGISTER_ERROR")
  case object ClientUpdate                        extends EventType("CLIENT_UPDATE")
  case object ClientUpdateError                   extends EventType("CLIENT_UPDATE_ERROR")
  case object CodeToToken                         extends EventType("CODE_TO_TOKEN")
  case object CodeToTokenError                    extends EventType("CODE_TO_TOKEN_ERROR")
  case object CustomRequiredAction                extends EventType("CUSTOM_REQUIRED_ACTION")
  case object CustomRequiredActionError           extends EventType("CUSTOM_REQUIRED_ACTION_ERROR")
  case object ExecuteActions                      extends EventType("EXECUTE_ACTIONS")
  case object ExecuteActionsError                 extends EventType("EXECUTE_ACTIONS_ERROR")
  case object ExecuteActionToken                  extends EventType("EXECUTE_ACTION_TOKEN")
  case object ExecuteActionTokenError             extends EventType("EXECUTE_ACTION_TOKEN_ERROR")
  case object FederatedIdentityLink               extends EventType("FEDERATED_IDENTITY_LINK")
  case object FederatedIdentityLinkError          extends EventType("FEDERATED_IDENTITY_LINK_ERROR")
  case object GrantConsent                        extends EventType("GRANT_CONSENT")
  case object GrantConsentError                   extends EventType("GRANT_CONSENT_ERROR")
  case object IdentityProviderFirstLogin          extends EventType("IDENTITY_PROVIDER_FIRST_LOGIN")
  case object IdentityProviderFirstLoginError     extends EventType("IDENTITY_PROVIDER_FIRST_LOGIN_ERROR")
  case object IdentityProviderLinkAccount         extends EventType("IDENTITY_PROVIDER_LINK_ACCOUNT")
  case object IdentityProviderLinkAccountError    extends EventType("IDENTITY_PROVIDER_LINK_ACCOUNT_ERROR")
  case object IdentityProviderLogin               extends EventType("IDENTITY_PROVIDER_LOGIN")
  case object IdentityProviderLoginError          extends EventType("IDENTITY_PROVIDER_LOGIN_ERROR")
  case object IdentityProviderPostLogin           extends EventType("IDENTITY_PROVIDER_POST_LOGIN")
  case object IdentityProviderPostLoginError      extends EventType("IDENTITY_PROVIDER_POST_LOGIN_ERROR")
  case object IdentityProviderResponse            extends EventType("IDENTITY_PROVIDER_RESPONSE")
  case object IdentityProviderResponseError       extends EventType("IDENTITY_PROVIDER_RESPONSE_ERROR")
  case object IdentityProviderRetrieveToken       extends EventType("IDENTITY_PROVIDER_RETRIEVE_TOKEN")
  case object IdentityProviderRetrieveTokenError  extends EventType("IDENTITY_PROVIDER_RETRIEVE_TOKEN_ERROR")
  case object Impersonate                         extends EventType("IMPERSONATE")
  case object ImpersonateError                    extends EventType("IMPERSONATE_ERROR")
  case object IntrospectToken                     extends EventType("INTROSPECT_TOKEN")
  case object IntrospectTokenError                extends EventType("INTROSPECT_TOKEN_ERROR")
  case object InvalidSignature                    extends EventType("INVALID_SIGNATURE")
  case object InvalidSignatureError               extends EventType("INVALID_SIGNATURE_ERROR")
  case object Login                               extends EventType("LOGIN")
  case object LoginError                          extends EventType("LOGIN_ERROR")
  case object Logout                              extends EventType("LOGOUT")
  case object LogoutError                         extends EventType("LOGOUT_ERROR")
  case object OAuth2DeviceAuth                    extends EventType("OAUTH2_DEVICE_AUTH")
  case object OAuth2DeviceAuthError               extends EventType("OAUTH2_DEVICE_AUTH_ERROR")
  case object OAuth2DeviceCodeToToken             extends EventType("OAUTH2_DEVICE_CODE_TO_TOKEN")
  case object OAuth2DeviceCodeToTokenError        extends EventType("OAUTH2_DEVICE_CODE_TO_TOKEN_ERROR")
  case object OAuth2DeviceVerifyUserCode          extends EventType("OAUTH2_DEVICE_VERIFY_USER_CODE")
  case object OAuth2DeviceVerifyUserCodeError     extends EventType("OAUTH2_DEVICE_VERIFY_USER_CODE_ERROR")
  case object PermissionToken                     extends EventType("PERMISSION_TOKEN")
  case object PermissionTokenError                extends EventType("PERMISSION_TOKEN_ERROR")
  case object RefreshToken                        extends EventType("REFRESH_TOKEN")
  case object RefreshTokenError                   extends EventType("REFRESH_TOKEN_ERROR")
  case object Register                            extends EventType("REGISTER")
  case object RegisterError                       extends EventType("REGISTER_ERROR")
  case object RegisterNode                        extends EventType("REGISTER_NODE")
  case object RegisterNodeError                   extends EventType("REGISTER_NODE_ERROR")
  case object RemoveFederatedIdentity             extends EventType("REMOVE_FEDERATED_IDENTITY")
  case object RemoveFederatedIdentityError        extends EventType("REMOVE_FEDERATED_IDENTITY_ERROR")
  case object RemoveTotp                          extends EventType("REMOVE_TOTP")
  case object RemoveTotpError                     extends EventType("REMOVE_TOTP_ERROR")
  case object ResetPassword                       extends EventType("RESET_PASSWORD")
  case object ResetPasswordError                  extends EventType("RESET_PASSWORD_ERROR")
  case object RestartAuthentication               extends EventType("RESTART_AUTHENTICATION")
  case object RestartAuthenticationError          extends EventType("RESTART_AUTHENTICATION_ERROR")
  case object RevokeGrant                         extends EventType("REVOKE_GRANT")
  case object RevokeGrantError                    extends EventType("REVOKE_GRANT_ERROR")
  case object SendIdentityProviderLink            extends EventType("SEND_IDENTITY_PROVIDER_LINK")
  case object SendIdentityProviderLinkError       extends EventType("SEND_IDENTITY_PROVIDER_LINK_ERROR")
  case object SendResetPassword                   extends EventType("SEND_RESET_PASSWORD")
  case object SendResetPasswordError              extends EventType("SEND_RESET_PASSWORD_ERROR")
  case object SendVerifyEmail                     extends EventType("SEND_VERIFY_EMAIL")
  case object SendVerifyEmailError                extends EventType("SEND_VERIFY_EMAIL_ERROR")
  case object TokenExchange                       extends EventType("TOKEN_EXCHANGE")
  case object TokenExchangeError                  extends EventType("TOKEN_EXCHANGE_ERROR")
  case object UnregisterNode                      extends EventType("UNREGISTER_NODE")
  case object UnregisterNodeError                 extends EventType("UNREGISTER_NODE_ERROR")
  case object UpdateConsent                       extends EventType("UPDATE_CONSENT")
  case object UpdateConsentError                  extends EventType("UPDATE_CONSENT_ERROR")
  case object UpdateEmail                         extends EventType("UPDATE_EMAIL")
  case object UpdateEmailError                    extends EventType("UPDATE_EMAIL_ERROR")
  case object UpdatePassword                      extends EventType("UPDATE_PASSWORD")
  case object UpdatePasswordError                 extends EventType("UPDATE_PASSWORD_ERROR")
  case object UpdateProfile                       extends EventType("UPDATE_PROFILE")
  case object UpdateProfileError                  extends EventType("UPDATE_PROFILE_ERROR")
  case object UpdateTotp                          extends EventType("UPDATE_TOTP")
  case object UpdateTotpError                     extends EventType("UPDATE_TOTP_ERROR")
  case object UserInfoRequest                     extends EventType("USER_INFO_REQUEST")
  case object UserInfoRequestError                extends EventType("USER_INFO_REQUEST_ERROR")
  case object ValidateAccessToken                 extends EventType("VALIDATE_ACCESS_TOKEN")
  case object ValidateAccessTokenError            extends EventType("VALIDATE_ACCESS_TOKEN_ERROR")
  case object VerifyEmail                         extends EventType("VERIFY_EMAIL")
  case object VerifyEmailError                    extends EventType("VERIFY_EMAIL_ERROR")

  val values: immutable.IndexedSeq[EventType] = findValues
}
