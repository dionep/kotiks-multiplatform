package com.dionep.kotiksmultiplatform.shared.mvi

import com.badoo.reaktive.disposable.scope.DisposableScope
import com.badoo.reaktive.disposable.scope.disposableScope
import com.badoo.reaktive.observable.map

abstract class Component<Model: Any, Event: Any, FeatureState: Any, FeatureIntent: Any>(
    private val stateMapper: (FeatureState) -> Model,
    private val eventMapper: (Event) -> FeatureIntent
) {

    abstract var view: MviView<Model, Event>?
    abstract val feature: Feature<FeatureIntent, FeatureState, *>

    private var startStopScope: DisposableScope? = null

    fun onViewCreated(view: MviView<Model, Event>) {
        this.view = view
    }

    fun onStart() {
        val view = requireNotNull(view)

        startStopScope = disposableScope {
            feature.map(stateMapper).subscribeScoped(onNext = view::render)
        }
    }

    fun accept(event: Event) {
        feature.onNext(eventMapper.invoke(event))
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