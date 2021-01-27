package com.dionep.kotiksmultiplatform.shared.cats.datasource

import com.badoo.reaktive.single.Single

internal interface CatsDataSource {
    fun load(): Single<String>
}