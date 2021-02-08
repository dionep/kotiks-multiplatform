package com.dionep.kotiksmultiplatform.repository

import com.badoo.reaktive.single.Single
import com.dionep.kotiksmultiplatform.network.NetworkSource
import com.dionep.kotiksmultiplatform.shared.model.Fact

class FactsRepository(
    private val networkSource: NetworkSource
) {

    fun getFacts(): Single<List<Fact>> =
        networkSource.getFacts()

    fun createFact(text: String): Single<Boolean> =
        networkSource.createFact(text)

    fun deleteFact(id: Int): Single<Boolean> =
        networkSource.deleteFact(id)

}