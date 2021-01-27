package com.dionep.kotiksmultiplatform.shared.cats.commonMain.datasource

import com.badoo.reaktive.single.Single

internal interface CatsDataSource {
    fun load(): Single<String>
}