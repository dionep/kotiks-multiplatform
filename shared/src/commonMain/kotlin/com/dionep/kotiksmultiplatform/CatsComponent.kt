package com.dionep.kotiksmultiplatform

import com.badoo.reaktive.disposable.scope.DisposableScope
import com.badoo.reaktive.disposable.scope.disposableScope
import com.badoo.reaktive.observable.map
import com.dionep.kotiksmultiplatform.CatsView.*
import com.dionep.kotiksmultiplatform.datasource.CatsDataSource
import com.dionep.kotiksmultiplatform.datasource.CatsDataSourceFactory
import com.dionep.kotiksmultiplatform.integration.CatsStoreParser
import com.dionep.kotiksmultiplatform.integration.toIntent
import com.dionep.kotiksmultiplatform.integration.toModel
import com.dionep.kotiksmultiplatform.store.CatsFeature.*
import com.dionep.kotiksmultiplatform.store.CatsFeatureImpl

class CatsComponent internal constructor(dataSource: CatsDataSource){

    constructor() : this(CatsDataSourceFactory())

    private val store = CatsFeatureImpl(
        dataSource = dataSource,
        parser = CatsStoreParser
    )

    private var view: CatsView? = null
    private var startStopScope: DisposableScope? = null

    fun onViewCreated(view: CatsView) {
        this.view = view
    }

    fun onStart() {
        val view = requireNotNull(view)

        startStopScope = disposableScope {
            store.map(State::toModel).subscribeScoped(onNext = view::render)
            view.events.map(Event::toIntent).subscribeScoped(onNext = store::onNext)
        }
    }

    fun onStop() {
        requireNotNull(startStopScope).dispose()
    }

    fun onViewDestroyed() {
        view = null
    }

    fun onDestroy() {
        store.dispose()
    }

}