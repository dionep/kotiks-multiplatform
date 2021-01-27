package com.dionep.kotiksmultiplatform.datasource

import com.badoo.reaktive.single.Single

internal interface CatsDataSource {
    fun load(): Single<String>
}