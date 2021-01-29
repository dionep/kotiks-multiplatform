package com.dionep.kotiksmultiplatform.integration

import com.badoo.reaktive.scheduler.computationScheduler
import com.badoo.reaktive.single.Single
import com.badoo.reaktive.single.singleFromFunction
import com.badoo.reaktive.single.subscribeOn
import com.dionep.kotiksmultiplatform.store.CatsStoreImpl
import com.dionep.kotiksmultiplatform.shared.model.Fact
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json


internal object CatsStoreParser : CatsStoreImpl.Parser {

    override fun parse(json: String): Single<List<Fact>> =
        singleFromFunction<List<Fact>> {
            Json {
                ignoreUnknownKeys = true
                isLenient = true
            }.decodeFromString(json)
        }
            .subscribeOn(computationScheduler)

}