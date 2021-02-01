package com.dionep.kotiksmultiplatform.shared.mvi

import com.badoo.reaktive.disposable.scope.DisposableScope
import com.badoo.reaktive.disposable.scope.disposableScope
import com.badoo.reaktive.observable.map

abstract class Component<
    FeatureState: Any,
    FeatureNews: Any,
    FeatureMsg: Any,
    UiState: Any,
    UiNews: Any,
    UiEvents: Any
>(
    private val stateMapper: (FeatureState) -> UiState,
    private val newsMapper: (FeatureNews) -> UiNews,
    private val uiEventsMapper: (UiEvents) -> FeatureMsg
) {

    abstract var view: MviView<UiState, UiNews, UiEvents>?
    abstract val feature: Feature<FeatureState, *, FeatureMsg, FeatureNews>

    private var startStopScope: DisposableScope? = null

    fun onViewCreated(view: MviView<UiState, UiNews, UiEvents>) {
        this.view = view
    }

    fun onStart() {
        val view = requireNotNull(view)

        startStopScope = disposableScope {
            feature.state.subscribeScoped {
                view.render(stateMapper.invoke(it))
            }
            feature.news.subscribeScoped {
                view.handleNews(newsMapper.invoke(it))
            }
        }
    }

    fun accept(uiEvents: UiEvents) {
        feature.accept(uiEventsMapper.invoke(uiEvents))
    }

    fun onStop() {
        requireNotNull(startStopScope).dispose()
    }

    fun onViewDestroyed() {
        view = null
    }

    fun onDestroy() {
        feature.dispose()
    }

}