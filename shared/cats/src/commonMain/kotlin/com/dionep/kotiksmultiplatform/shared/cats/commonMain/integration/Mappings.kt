package com.dionep.kotiksmultiplatform.shared.cats.commonMain.integration

import com.dionep.kotiksmultiplatform.shared.cats.commonMain.CatsView.*
import com.dionep.kotiksmultiplatform.shared.cats.commonMain.store.CatsStore.*

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
