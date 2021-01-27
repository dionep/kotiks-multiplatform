package com.dionep.kotiksmultiplatform.shared.cats.commonMain

import com.badoo.reaktive.disposable.scope.DisposableScope
import com.badoo.reaktive.disposable.scope.disposableScope
import com.badoo.reaktive.observable.map
import com.dionep.kotiksmultiplatform.shared.cats.commonMain.CatsView.*
import com.dionep.kotiksmultiplatform.shared.cats.commonMain.datasource.CatsDataSource
import com.dionep.kotiksmultiplatform.shared.cats.commonMain.integration.CatsStoreNetwork
import com.dionep.kotiksmultiplatform.shared.cats.commonMain.integration.CatsStoreParser
import com.dionep.kotiksmultiplatform.shared.cats.commonMain.integration.toIntent
import com.dionep.kotiksmultiplatform.shared.cats.commonMain.integration.toModel
import com.dionep.kotiksmultiplatform.shared.cats.commonMain.store.CatsStore.*
import com.dionep.kotiksmultiplatform.shared.cats.commonMain.store.CatsStoreImpl

class CatsComponent internal constructor(dataSource: CatsDataSource){

    private val store = CatsStoreImpl(
        network = CatsStoreNetwork(dataSource = dataSource),
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