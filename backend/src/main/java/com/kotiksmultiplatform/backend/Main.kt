package com.kotiksmultiplatform.backend

import com.google.gson.Gson
import com.kotiksmultiplatform.backend.entities.Fact
import io.ktor.server.engine.*
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.netty.*

fun main(args: Array<String>) {
    embeddedServer(Netty, 8080) {
        routing {
            get("/facts")  {
                call.respondText(
                    Gson().toJson(listOf(Fact("1", "Fact #1"))), ContentType.Application.Json
                )
            }
        }
    }.start(wait = true)
}