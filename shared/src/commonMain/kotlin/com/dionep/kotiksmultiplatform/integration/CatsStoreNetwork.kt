package com.dionep.kotiksmultiplatform.integration

import com.badoo.reaktive.single.Single
import com.dionep.kotiksmultiplatform.datasource.CatsDataSource
import com.dionep.kotiksmultiplatform.store.CatsFeatureImpl

internal class CatsStoreNetwork(
    private val dataSource: CatsDataSource
): CatsFeatureImpl.Network {
    override fun load(): Single<String> = dataSource.load()
}