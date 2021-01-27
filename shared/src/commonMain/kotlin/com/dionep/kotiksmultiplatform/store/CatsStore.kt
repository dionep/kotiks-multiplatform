package com.dionep.kotiksmultiplatform.store

import com.dionep.kotiksmultiplatform.shared.mvi.Store
import com.dionep.kotiksmultiplatform.store.CatsStore.*

internal interface CatsStore : Store<Intent, State> {

    sealed class Intent {
        object Load : Intent()
    }

    data class State(
        val isLoading: Boolean = false,
        val data: Data = Data.CatFacts()
    ) {

        sealed class Data {
            data class CatFacts(val facts: List<String> = emptyList()) : Data()
            object Error : Data()
        }

    }

}