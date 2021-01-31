package com.dionep.kotiksmultiplatform.store

import com.badoo.reaktive.observable.*
import com.badoo.reaktive.scheduler.mainScheduler
import com.badoo.reaktive.single.*
import com.dionep.kotiksmultiplatform.datasource.CatsDataSource
import com.dionep.kotiksmultiplatform.shared.model.Fact
import com.dionep.kotiksmultiplatform.shared.mvi.Feature
import com.dionep.kotiksmultiplatform.store.CatsFeatureImpl.*

internal class CatsFeatureImpl(
    private val dataSource: CatsDataSource,
    private val parser: Parser
): Feature<Intent, State, Effect>(
    initialState = State(),
    reducer = { state, effect ->
        when(effect) {
            is Effect.StartLoading -> state.copy(isLoading = true)
            is Effect.SuccessLoaded -> state.copy(isLoading = false, data = State.Data.CatFacts(effect.imageUrls))
            is Effect.Failure -> state.copy(isLoading = false, data = State.Data.Error, error = effect.throwable)
        }
    },
    actor = { state, intent ->
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
    }
) {


    init {
        onNext(Intent.Load)
    }

    interface Parser {
        fun parse(json: String): Single<List<Fact>>
    }

    sealed class Intent {
        object Load : Intent()
    }

    data class State(
        val isLoading: Boolean = false,
        val data: Data = Data.CatFacts(),
        val error: Throwable? = null
    ) {

        sealed class Data {
            data class CatFacts(val facts: List<String> = emptyList()) : Data()
            object Error : Data()
        }

    }

    sealed class Effect {
        object StartLoading : Effect()
        data class SuccessLoaded(val imageUrls: List<String>) : Effect()
        data class Failure(val throwable: Throwable) : Effect()
    }


}