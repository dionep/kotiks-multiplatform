package com.dionep.kotiksmultiplatform.network

import com.badoo.reaktive.coroutinesinterop.singleFromCoroutine
import com.badoo.reaktive.single.Single
import com.dionep.kotiksmultiplatform.Constants
import com.dionep.kotiksmultiplatform.shared.model.Fact
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*

class NetworkSourceImpl : NetworkSource {

    private val httpClient: HttpClient = HttpClient {
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
            httpClient.get(Constants.Api.BASE_URL + "facts")
        }

}