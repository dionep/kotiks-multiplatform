package com.dionep.kotiksmultiplatform.integration

import com.dionep.kotiksmultiplatform.CatsView
import com.dionep.kotiksmultiplatform.CatsView.*
import com.dionep.kotiksmultiplatform.store.CatsStore.*

internal fun State.toModel(): Model =
    Model(
        isLoading = isLoading,
        isError = when (data) {
            is State.Data.CatFacts -> false
            is State.Data.Error -> true
        },
        catsFacts = when (data) {
            is State.Data.CatFacts -> data.facts
            is State.Data.Error -> emptyList()
        }
    )

internal fun Event.toIntent(): Intent =
    when (this) {
        is Event.Load -> Intent.Load
    }
