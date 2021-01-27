package com.dionep.kotiksmultiplatform.shared.cats.integration

import com.badoo.reaktive.scheduler.computationScheduler
import com.badoo.reaktive.single.Single
import com.badoo.reaktive.single.singleFromFunction
import com.badoo.reaktive.single.subscribeOn
import com.dionep.kotiksmultiplatform.shared.cats.store.CatsStoreImpl
import com.dionep.kotiksmultiplatform.shared.model.Fact
import kotlinx.serialization.ImplicitReflectionSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.parseList


internal object CatsStoreParser : CatsStoreImpl.Parser {

    @ImplicitReflectionSerializer
    override fun parse(json: String): Single<List<Fact>> =
        singleFromFunction<List<Fact>> { Json.parseList(json) }
            .subscribeOn(computationScheduler)

}