package com.dionep.kotiksmultiplatform.features

import com.badoo.reaktive.observable.onErrorReturn
import com.badoo.reaktive.scheduler.ioScheduler
import com.badoo.reaktive.scheduler.mainScheduler
import com.badoo.reaktive.single.*
import com.dionep.kotiksmultiplatform.features.FactsFeatureComponent.*
import com.dionep.kotiksmultiplatform.base.MviComponent
import com.dionep.kotiksmultiplatform.repository.FactsRepository
import com.dionep.kotiksmultiplatform.shared.mvi.*
import org.koin.core.*

class FactsFeatureComponent : MviComponent<State, Msg, News>(), KoinComponent {

    private val factsRepository by inject<FactsRepository>()

    override val feature = Feature<State, Cmd, Msg, News>(
        initialState = State(),
        initialMessages = setOf(Msg.Load),
        reducer = { msg, state ->
            when (msg) {
                is Msg.Load -> Update(state.copy(isLoading = true), Cmd.Load)
                is Msg.StopLoading -> Update(state.copy(isLoading = false))
                is Msg.SetFacts -> Update(state.copy(isLoading = false, facts = msg.facts))
            }
        },
        commandHandler = { cmd ->
            when (cmd) {
                is Cmd.Load ->
                    factsRepository.getFacts()
                        .map { SideEffect<Msg, News>(Msg.SetFacts(it)) }
                        .subscribeOn(ioScheduler)
                        .observeOn(mainScheduler)
                        .asObservable()
                        .onErrorReturn { SideEffect(Msg.StopLoading, News.Failure(it)) }
            }
        },
        stateListener = { stateListener.invoke(it) },
        newsListener = { newsListener.invoke(it) }
    )

    data class State(
        val isLoading: Boolean = false,
        val facts: List<String> = listOf()
    )

    sealed class Cmd {
        object Load : Cmd()
    }

    sealed class Msg {
        object Load : Msg()
        object StopLoading : Msg()
        data class SetFacts(val facts: List<String>) : Msg()
    }

    sealed class News {
        data class Failure(val throwable: Throwable) : News()
    }

}