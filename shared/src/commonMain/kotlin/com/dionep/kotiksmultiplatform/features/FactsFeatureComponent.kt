package com.dionep.kotiksmultiplatform.features

import com.badoo.reaktive.observable.onErrorReturn
import com.badoo.reaktive.scheduler.ioScheduler
import com.badoo.reaktive.scheduler.mainScheduler
import com.badoo.reaktive.single.*
import com.badoo.reaktive.observable.map
import com.dionep.kotiksmultiplatform.features.FactsFeatureComponent.*
import com.dionep.kotiksmultiplatform.base.MviComponent
import com.dionep.kotiksmultiplatform.features.changes.Changes
import com.dionep.kotiksmultiplatform.repository.FactsRepository
import com.dionep.kotiksmultiplatform.shared.model.Fact
import com.dionep.kotiksmultiplatform.shared.mvi.*
import org.koin.core.*
import com.badoo.reaktive.observable.observeOn

class FactsFeatureComponent : MviComponent<State, Msg, News>(), KoinComponent {

    private val factsRepository by inject<FactsRepository>()
    private val changes by inject<Changes>()

    override val feature = Feature<State, Cmd, Msg, News>(
        initialState = State(),
        initialMessages = setOf(Msg.Load),
        reducer = { msg, state ->
            when (msg) {
                is Msg.Load -> Update(state.copy(isLoading = true), Cmd.Load)
                is Msg.StopLoading -> Update(state.copy(isLoading = false))
                is Msg.SetFacts -> Update(state.copy(isLoading = false, facts = msg.facts))
                is Msg.DeleteFact -> Update(state.copy(isLoading = true), Cmd.DeleteFact(msg.id))
                is Msg.FactDeleted -> Update(cmd = Cmd.Load)
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
                is Cmd.DeleteFact ->
                    factsRepository.deleteFact(cmd.id)
                        .map { SideEffect<Msg, News>(Msg.FactDeleted) }
                        .subscribeOn(ioScheduler)
                        .observeOn(mainScheduler)
                        .asObservable()
                        .onErrorReturn { SideEffect(Msg.StopLoading, News.Failure(it)) }
            }
        },
        bootstrapper = setOf(
            changes.factChangedObservable
                .map { Msg.Load }
        ),
        stateListener = { stateListener.invoke(it) },
        newsListener = { newsListener.invoke(it) }
    )

    data class State(
        val isLoading: Boolean = false,
        val facts: List<Fact> = listOf()
    )

    sealed class Cmd {
        object Load : Cmd()
        data class DeleteFact(val id: Int) : Cmd()
    }

    sealed class Msg {
        object Load : Msg()
        object StopLoading : Msg()
        data class SetFacts(val facts: List<Fact>) : Msg()
        data class DeleteFact(val id: Int) : Msg()
        object FactDeleted : Msg()
    }

    sealed class News {
        data class Failure(val throwable: Throwable) : News()
    }

}