package com.dionep.kotiksmultiplatform.shared.cats.integration

import com.badoo.reaktive.single.Single
import com.dionep.kotiksmultiplatform.shared.cats.datasource.CatsDataSource
import com.dionep.kotiksmultiplatform.shared.cats.store.CatsStoreImpl

internal class CatsStoreNetwork(
    private val dataSource: CatsDataSource
): CatsStoreImpl.Network {
    override fun load(): Single<String> = dataSource.load()
}