package com.dionep.kotiksmultiplatform.store

import com.badoo.reaktive.disposable.scope.DisposableScope
import com.badoo.reaktive.observable.*
import com.badoo.reaktive.scheduler.mainScheduler
import com.badoo.reaktive.single.*
import com.dionep.kotiksmultiplatform.datasource.CatsDataSource
import com.dionep.kotiksmultiplatform.shared.model.Fact
import com.dionep.kotiksmultiplatform.shared.mvi.FeatureHelper
import com.dionep.kotiksmultiplatform.store.CatsFeature.Intent
import com.dionep.kotiksmultiplatform.store.CatsFeature.State

internal class CatsFeatureImpl(
    private val dataSource: CatsDataSource,
    private val parser: Parser
): CatsFeature, DisposableScope by DisposableScope() {

    private val helper = FeatureHelper(State(), ::handleIntent, ::reduce).scope()
    override val state: State = helper.state

    init {
        helper.onIntent(Intent.Load)
    }

    override fun onNext(value: Intent) {
        helper.onIntent(value)
    }

    override fun subscribe(observer: ObservableObserver<State>) {
        helper.subscribe(observer)
    }

    private fun handleIntent(state: State, intent: Intent): Observable<Effect> =
        when (intent) {
            is Intent.Load ->
                dataSource.load()
                    .flatMap(parser::parse)
                    .flatMapIterable { it }
                    .map { it.text }
                    .toList()
                    .map(Effect::SuccessLoaded)
                    .observeOn(mainScheduler)
                    .asObservable()
                    .startWithValue(Effect.StartLoading)
                    .onErrorReturn { Effect.Failure(it) }
        }


    private fun reduce(state: State, effect: Effect): State =
        when(effect) {
            is Effect.StartLoading -> state.copy(isLoading = true)
            is Effect.SuccessLoaded -> state.copy(isLoading = false, data = State.Data.CatFacts(effect.imageUrls))
            is Effect.Failure -> state.copy(isLoading = false, data = State.Data.Error, error = effect.throwable)
        }

    interface Parser {
        fun parse(json: String): Single<List<Fact>>
    }

    private sealed class Effect {
        object StartLoading : Effect()
        data class SuccessLoaded(val imageUrls: List<String>) : Effect()
        data class Failure(val throwable: Throwable) : Effect()
    }

}