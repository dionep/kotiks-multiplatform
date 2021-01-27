package com.dionep.kotiksmultiplatform.shared.cats.datasource

import com.badoo.reaktive.single.Single
import com.dionep.kotiksmultiplatform.shared.model.Fact

internal interface CatsDataSource {
    fun load(): Single<Fact>
}