package com.dionep.kotiksmultiplatform.repository

import com.badoo.reaktive.observable.map
import com.badoo.reaktive.observable.toList
import com.badoo.reaktive.single.Single
import com.badoo.reaktive.single.flatMapIterable
import com.dionep.kotiksmultiplatform.network.NetworkSource

class FactsRepository(
    private val networkSource: NetworkSource
) {

    fun getFacts(): Single<List<String>> =
        networkSource.getFacts()
            .flatMapIterable { it }
            .map { it.text }
            .toList()


}