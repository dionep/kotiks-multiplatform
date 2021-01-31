package com.dionep.kotiksmultiplatform.store

import com.dionep.kotiksmultiplatform.shared.mvi.Feature
import com.dionep.kotiksmultiplatform.store.CatsFeature.*

internal interface CatsFeature : Feature<Intent, State> {

    sealed class Intent {
        object Load : Intent()
    }

    data class State(
        val isLoading: Boolean = false,
        val data: Data = Data.CatFacts(),
        val error: Throwable? = null
    ) {

        sealed class Data {
            data class CatFacts(val facts: List<String> = emptyList()) : Data()
            object Error : Data()
        }

    }

}