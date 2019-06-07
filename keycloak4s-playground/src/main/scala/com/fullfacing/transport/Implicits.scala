package com.fullfacing.transport

import com.fullfacing.keycloak4s.auth.akka.http.services.TokenValidator
import monix.execution.Scheduler

object Implicits {
  implicit val scheduler: Scheduler = Scheduler.io("io")
  implicit val tv: TokenValidator = new TokenValidator("http", "localhost", 8088, "test")
}
