package com.lambao.routes

import com.lambao.models.params.RegisterParams
import com.lambao.repository.user.UserRepository
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureAuth(repository: UserRepository) {
    routing {
        route("/auth") {
            post("/register") {
                val params = call.receive<RegisterParams>()
                val result = repository.register(params)
                call.respond(result.statusCode, result)
            }
        }
    }
}