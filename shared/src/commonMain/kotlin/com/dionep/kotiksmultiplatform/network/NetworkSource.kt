package com.dionep.kotiksmultiplatform.network

import com.badoo.reaktive.single.Single
import com.dionep.kotiksmultiplatform.shared.model.Fact

interface NetworkSource {
    fun getFacts(): Single<List<Fact>>
    fun createFact(text: String): Single<Boolean>
}