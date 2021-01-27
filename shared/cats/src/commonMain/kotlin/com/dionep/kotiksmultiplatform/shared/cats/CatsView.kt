package com.dionep.kotiksmultiplatform.shared.cats

import com.dionep.kotiksmultiplatform.shared.cats.CatsView.*
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