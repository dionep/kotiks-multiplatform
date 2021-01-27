package com.dionep.kotiksmultiplatform.shared.cats.store

import com.dionep.kotiksmultiplatform.shared.mvi.Store
import com.dionep.kotiksmultiplatform.shared.cats.store.CatsStore.*

internal interface CatsStore : Store<Intent, State> {

    sealed class Intent {
        object Reload : Intent()
    }

    data class State(
        val isLoading: Boolean = false,
        val catsList: Data = Data.CatNames()
    ) {

        sealed class Data {
            data class CatNames(val names: List<String> = emptyList()) : Data()
            object Error : Data()
        }

    }

}