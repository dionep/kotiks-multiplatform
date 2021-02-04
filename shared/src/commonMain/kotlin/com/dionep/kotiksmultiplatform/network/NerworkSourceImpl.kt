package com.dionep.kotiksmultiplatform.network

import com.badoo.reaktive.coroutinesinterop.singleFromCoroutine
import com.badoo.reaktive.single.Single
import com.dionep.kotiksmultiplatform.Constants
import com.dionep.kotiksmultiplatform.shared.model.Fact
import com.dionep.kotiksmultiplatform.shared.model.request.CreateFactRequest
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.http.*

class NetworkSourceImpl : NetworkSource {

    private val httpClient: HttpClient = HttpClient {
        expectSuccess = false
        install(JsonFeature) {
            serializer = KotlinxSerializer(
                kotlinx.serialization.json.Json {
                    isLenient = true
                    ignoreUnknownKeys = true
                }
            )
        }
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.BODY
        }
    }

    override fun getFacts(): Single<List<Fact>> =
        singleFromCoroutine {
            httpClient.get(EndPoints.Facts.get()) {
                contentType(ContentType.Application.Json)
            }
        }

    override fun createFact(text: String): Single<Boolean> =
        singleFromCoroutine {
            httpClient.post(EndPoints.Facts.get()) {
                contentType(ContentType.Application.Json)
                body = CreateFactRequest(text)
            }
        }

    companion object {
        object EndPoints {
            object Facts : EndPoint("facts")
        }

        abstract class EndPoint(private val endpoint: String) {
            fun get() = Constants.Api.BASE_URL + endpoint
        }
    }

}