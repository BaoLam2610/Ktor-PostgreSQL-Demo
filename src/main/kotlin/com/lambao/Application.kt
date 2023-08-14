package com.lambao

import com.lambao.database.DatabaseFactory
import com.lambao.repository.user.UserRepository
import com.lambao.repository.user.UserRepositoryImpl
import com.lambao.routes.configureAuth
import com.lambao.services.user.UserService
import com.lambao.services.user.UserServiceImpl
import io.ktor.serialization.jackson.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    DatabaseFactory.init()

    install(ContentNegotiation) {
        jackson()
    }
    val service: UserService = UserServiceImpl()
    val repository: UserRepository = UserRepositoryImpl(service)
    configureAuth(repository)
}
