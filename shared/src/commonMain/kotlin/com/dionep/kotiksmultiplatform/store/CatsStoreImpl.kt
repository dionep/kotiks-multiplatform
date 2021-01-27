package com.dionep.kotiksmultiplatform.store

import com.badoo.reaktive.disposable.scope.DisposableScope
import com.badoo.reaktive.observable.*
import com.badoo.reaktive.scheduler.mainScheduler
import com.badoo.reaktive.single.*
import com.dionep.kotiksmultiplatform.shared.model.Fact
import com.dionep.kotiksmultiplatform.store.CatsStore.Intent
import com.dionep.kotiksmultiplatform.store.CatsStore.State

internal class CatsStoreImpl(
    private val network: Network,
    private val parser: Parser
): CatsStore, DisposableScope by DisposableScope() {
    override val state: State = State()

    override fun onNext(value: Intent) {
    }

    override fun subscribe(observer: ObservableObserver<State>) {
    }

    private fun load(network: Network, parser: Parser) =
        network.load()
            .flatMap(parser::parse)
            .flatMapIterable { it }
            .map { it.text }
            .toList()
            .map(Effect::SuccessLoaded)
            .observeOn(mainScheduler)
            .asObservable()
            .defaultIfEmpty(Effect.Failure)
            .startWithValue(Effect.StartLoading)
            .onErrorComplete()

    private fun reduce(state: State, effect: Effect): State =
        when(effect) {
            is Effect.StartLoading -> state.copy(isLoading = true)
            is Effect.SuccessLoaded -> state.copy(isLoading = false, data = State.Data.CatFacts(effect.imageUrls))
            is Effect.Failure -> state.copy(isLoading = false, data = State.Data.Error)
        }

    interface Network {
        fun load(): Single<String>
    }

    interface Parser {
        fun parse(json: String): Single<List<Fact>>
    }

    private sealed class Effect {
        object StartLoading : Effect()
        data class SuccessLoaded(val imageUrls: List<String>) : Effect()
        object Failure : Effect()
    }

}