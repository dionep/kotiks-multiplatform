package com.dionep.kotiksmultiplatform.shared.cats.commonMain

import com.dionep.kotiksmultiplatform.shared.cats.commonMain.CatsView.*
import com.dionep.kotiksmultiplatform.shared.mvi.MviView

interface CatsView: MviView<Model, Event> {

    data class Model(
        val isLoading: Boolean,
        val isError: Boolean,
        val catsFacts: List<String>
    )

    sealed class Event {
        object Load : Event()
    }

}