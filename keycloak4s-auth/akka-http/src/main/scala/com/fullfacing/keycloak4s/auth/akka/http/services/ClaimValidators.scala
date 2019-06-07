package com.fullfacing.keycloak4s.auth.akka.http.services

import java.util.Date

import cats.data.Validated.{invalidNel, valid}
import cats.data.ValidatedNel
import com.fullfacing.keycloak4s.core.Exceptions
import com.fullfacing.keycloak4s.core.models.KeycloakException
import com.nimbusds.jwt.JWTClaimsSet

trait ClaimValidators {

  /* Validates that a token has not expired. **/
  protected def validateExp(claims: JWTClaimsSet, now: Date): ValidatedNel[KeycloakException, Unit] = {
    val exp   = claims.getExpirationTime
    val cond  = now.compareTo(exp) < 0

    if (cond) valid(()) else invalidNel(Exceptions.EXPIRED)
  }

  /* Validates that a token is not being used before its not-before time (if defined). **/
  protected def validateNbf(claims: JWTClaimsSet, now: Date): ValidatedNel[KeycloakException, Unit] = {
    val nbf   = Option(claims.getNotBeforeTime)
    val cond  = nbf.fold(true)(n => n == new Date(0) || now.compareTo(n) > 0)

    if (cond) valid(()) else invalidNel(Exceptions.NOT_YET_VALID)
  }

  /* Validates that a token's 'issued at' time exists and is not in the future. **/
  protected def validateIat(claims: JWTClaimsSet, now: Date): ValidatedNel[KeycloakException, Unit] = {
    val iat   = Option(claims.getIssueTime)
    val cond  = iat.map(now.compareTo(_) > 0)

    lazy val missing    = invalidNel[KeycloakException, Unit](Exceptions.IAT_MISSING)
    lazy val incorrect  = invalidNel[KeycloakException, Unit](Exceptions.IAT_INCORRECT)

    cond.fold(missing) {
      case true   => valid(())
      case false  => incorrect
    }
  }

  /* Validates that a token's issuer is defined and matches the web address as specified in the KeycloakConfig. **/
  protected def validateIss(claims: JWTClaimsSet, uri: String): ValidatedNel[KeycloakException, Unit] = {
    val iss = Option(claims.getIssuer)
    val cond = iss.map(_ == uri)

    lazy val missing    = invalidNel[KeycloakException, Unit](Exceptions.ISS_MISSING)
    lazy val incorrect  = invalidNel[KeycloakException, Unit](Exceptions.ISS_INCORRECT)

    cond.fold(missing) {
      case true   => valid(())
      case false  => incorrect
    }
  }
}
