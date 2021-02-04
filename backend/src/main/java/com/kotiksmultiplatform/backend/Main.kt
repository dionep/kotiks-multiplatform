package com.kotiksmultiplatform.backend

import com.google.gson.GsonBuilder
import com.kotiksmultiplatform.backend.repo.FactsRepository
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main(args: Array<String>) {
    embeddedServer(
        factory = Netty,
        port = 8080,
        module = Application::module
    ).start(wait = true)
}

fun Application.module() {
    install(ContentNegotiation) {
        register(
            ContentType.Application.Json,
            GsonConverter(
                GsonBuilder()
                    .setPrettyPrinting()
                    .serializeNulls()
                    .create()
            )
        )
    }

    DatabaseConfig.connect()

    val repo = FactsRepository()

    install(DefaultHeaders)
    install(CallLogging)
    install(Routing) {
        get("/facts")  {
            call.respond(repo.getFacts())
        }
    }

}