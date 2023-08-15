package com.lambao.security

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*

fun Application.configureSecurity() {
    JwtConfig.init("my-fucking-story")
    install(Authentication) {
        jwt {
            verifier(JwtConfig.instance.verify)
            validate {
                val claim = it.payload.getClaim(JwtConfig.CLAIM).asInt()
                if (claim != null) UserIdPrincipalForUser(claim)
                else null
            }
        }
    }
}