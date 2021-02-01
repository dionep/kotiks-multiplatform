package com.dionep.kotiksmultiplatform

import com.dionep.kotiksmultiplatform.CatsView.*
import com.dionep.kotiksmultiplatform.shared.mvi.MviView

interface CatsView : MviView<UiState, UiNews, UiEvents> {

    data class UiState(
        val isLoading: Boolean,
        val facts: List<String> = listOf()
    )

    sealed class UiEvents {
        object Load : UiEvents()
    }

    sealed class UiNews {
        object Error : UiNews()
    }

}