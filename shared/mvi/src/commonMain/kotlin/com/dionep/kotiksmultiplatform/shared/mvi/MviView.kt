package com.dionep.kotiksmultiplatform.shared.mvi

interface MviView<in UiState : Any, in UiNews : Any, out UiEvents> {

    fun render(model: UiState)
    fun handleNews(news: UiNews)

}
