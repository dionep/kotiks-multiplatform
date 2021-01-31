package com.dionep.kotiksmultiplatform.datasource

import com.badoo.reaktive.single.Single

interface CatsDataSource {
    fun load(): Single<String>
}