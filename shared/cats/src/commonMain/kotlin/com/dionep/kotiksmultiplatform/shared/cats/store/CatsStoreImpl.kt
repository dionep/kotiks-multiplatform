package com.dionep.kotiksmultiplatform.shared.cats.store

import com.badoo.reaktive.disposable.scope.DisposableScope
import com.badoo.reaktive.maybe.*
import com.badoo.reaktive.observable.ObservableObserver
import com.badoo.reaktive.observable.defaultIfEmpty
import com.badoo.reaktive.observable.startWithValue
import com.badoo.reaktive.scheduler.mainScheduler
import com.dionep.kotiksmultiplatform.shared.cats.store.CatsStore.*
import kotlin.contracts.Effect

internal class CatsStoreImpl(
    private val network: Network,
    private val parser: Parser
): CatsStore, DisposableScope by DisposableScope() {
    override val state: State = State()

    override fun onNext(value: Intent) {
        TODO("Not yet implemented")
    }

    override fun subscribe(observer: ObservableObserver<State>) {
        TODO("Not yet implemented")
    }

    private fun load(network: Network, parser: Parser) =
        network.load()
            .flatMap(parser::parse)
            .map(Effect::SuccessLoaded)
            .observeOn(mainScheduler)
            .asObservable()
            .defaultIfEmpty(Effect.Failure)
            .startWithValue(Effect.StartLoading)

    private fun reduce(state: State, effect: Effect): State =
        when(effect) {
            is Effect.StartLoading -> state.copy(isLoading = true)
            is Effect.SuccessLoaded -> state.copy(isLoading = false, catsList = State.Data.CatNames(effect.imageUrls))
            is Effect.Failure -> state.copy(isLoading = false, catsList = State.Data.Error)
        }

    interface Network {
        fun load(): Maybe<String>
    }

    interface Parser {
        fun parse(json: String): Maybe<List<String>>
    }

    private sealed class Effect {
        object StartLoading : Effect()
        data class SuccessLoaded(val imageUrls: List<String>) : Effect()
        object Failure : Effect()
    }

}