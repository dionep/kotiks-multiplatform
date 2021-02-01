package com.dionep.kotiksmultiplatform.base

import com.dionep.kotiksmultiplatform.shared.mvi.Feature

abstract class MviComponent<State, Msg: Any, News> {

    internal var stateListener: (State) -> Unit = {}
    internal var newsListener: (News) -> Unit = {}
    abstract val feature: Feature<State, *, Msg, News>

    fun bindListeners(
        stateListener: (State) -> Unit,
        newsListener: (News) -> Unit
    ) {
        this.stateListener = stateListener
        this.newsListener = newsListener
        stateListener.invoke(feature.getCurrentState())
    }

    fun dispose() {
        feature.dispose()
        stateListener = {}
        newsListener = {}
    }

    fun accept(msg: Msg) {
        feature.accept(msg)
    }

}