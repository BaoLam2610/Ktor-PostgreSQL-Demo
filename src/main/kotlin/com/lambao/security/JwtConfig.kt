package com.lambao.security

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm

class JwtConfig private constructor(secret: String){

    companion object {
        private const val ISSUER = "my-fucking-story"
        private const val AUDIENCE = ISSUER
        const val CLAIM = "id"

        lateinit var instance : JwtConfig
            private set

        fun init(secret: String) {
            synchronized(this) {
                if(!this::instance.isInitialized) {
                    instance = JwtConfig(secret)
                }
            }
        }
    }

    private val algorithm = Algorithm.HMAC256(secret)

    val verify: JWTVerifier = JWT
        .require(algorithm)
        .withIssuer(ISSUER)
        .withAudience(AUDIENCE)
        .build()

    fun createAccessToken(id: Int): String = JWT.create()
        .withIssuer(ISSUER)
        .withAudience(AUDIENCE)
        .withClaim(CLAIM, id)
        .sign(algorithm)
}