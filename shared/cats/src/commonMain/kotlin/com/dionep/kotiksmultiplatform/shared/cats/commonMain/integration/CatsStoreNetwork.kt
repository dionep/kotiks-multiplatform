package com.dionep.kotiksmultiplatform.shared.cats.commonMain.integration

import com.badoo.reaktive.single.Single
import com.dionep.kotiksmultiplatform.shared.cats.commonMain.datasource.CatsDataSource
import com.dionep.kotiksmultiplatform.shared.cats.commonMain.store.CatsStoreImpl

internal class CatsStoreNetwork(
    private val dataSource: CatsDataSource
): CatsStoreImpl.Network {
    override fun load(): Single<String> = dataSource.load()
}