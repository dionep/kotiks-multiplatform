package com.kotiksmultiplatform.backend

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.kotiksmultiplatform.backend.dao.FactsDao
import com.kotiksmultiplatform.backend.dao.FactsDaoImpl
import com.kotiksmultiplatform.backend.entities.Fact
import io.ktor.server.engine.*
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.netty.*
import org.jetbrains.exposed.sql.Database

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

    val dao: FactsDao = FactsDaoImpl(Database.connect("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;", driver = "org.h2.Driver")).init()

    install(DefaultHeaders)
    install(CallLogging)
    install(Routing) {
        get("/facts")  {
            call.respondText(
                Gson().toJson(listOf(Fact("Fact #1"))), ContentType.Application.Json
            )
        }
        post("/addFact") {
            val fact = call.receive<Fact>()
            dao.createFact(fact.text)
            call.respond("true")
        }
    }

}