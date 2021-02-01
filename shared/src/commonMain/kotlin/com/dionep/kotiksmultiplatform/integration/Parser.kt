package com.dionep.kotiksmultiplatform.integration

import com.badoo.reaktive.single.Single
import com.dionep.kotiksmultiplatform.shared.model.Fact

interface Parser {
    fun parse(json: String): Single<List<Fact>>
}