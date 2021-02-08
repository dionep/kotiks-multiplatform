package com.kotiksmultiplatform.backend.routing

import com.kotiksmultiplatform.backend.entities.FactRequest
import com.kotiksmultiplatform.backend.repo.FactsRepository
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.facts() {

    val repo = FactsRepository()

    route("/facts") {

        get {
            call.respond(repo.getFacts())
        }

        post {
            call.receiveOrNull<FactRequest>()?.let {
                repo.createFact(it.text)
                call.respond(true)
            } ?: call.respond(HttpStatusCode.BadRequest)
        }

        delete("/{id}") {
            call.parameters["id"]?.toIntOrNull()?.let {
                repo.deleteFact(it)
                call.respond(true)
            } ?: call.respond(HttpStatusCode.BadRequest)
        }

    }

}