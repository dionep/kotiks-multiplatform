package com.kotiksmultiplatform.backend

import com.google.gson.Gson
import com.kotiksmultiplatform.backend.entities.Fact
import io.ktor.server.engine.*
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.netty.*

fun main(args: Array<String>) {
    embeddedServer(
        factory = Netty,
        port = 8080,
        module = Application::module
    ).start(wait = true)
}

fun Application.module() {
    install(DefaultHeaders)
    install(CallLogging)
    install(Routing) {
        get("/facts")  {
            call.respondText(
                Gson().toJson(listOf(Fact("1", "Fact #1"))), ContentType.Application.Json
            )
        }
    }
}