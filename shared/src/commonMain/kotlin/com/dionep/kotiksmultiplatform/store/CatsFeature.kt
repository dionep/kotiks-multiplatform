package com.dionep.kotiksmultiplatform.store

import com.badoo.reaktive.observable.*
import com.badoo.reaktive.scheduler.mainScheduler
import com.badoo.reaktive.single.*
import com.dionep.kotiksmultiplatform.datasource.CatsDataSource
import com.dionep.kotiksmultiplatform.shared.model.Fact
import com.dionep.kotiksmultiplatform.shared.mvi.Feature
import com.dionep.kotiksmultiplatform.shared.mvi.SideEffect
import com.dionep.kotiksmultiplatform.shared.mvi.Update
import com.dionep.kotiksmultiplatform.store.CatsFeature.*

class CatsFeature(
    private val dataSource: CatsDataSource,
    private val parser: Parser
): Feature<State, Cmd, Msg, News>(
    initialState = State(),
    initialMessages = setOf(Msg.Load),
    reducer = { msg, state ->
        println("rere  $msg")
        when (msg) {
            is Msg.Load -> Update(state.copy(isLoading = true), Cmd.Load)
            is Msg.StopLoading -> Update(state.copy(isLoading = false))
            is Msg.SetFacts -> Update(state.copy(isLoading = false, facts = msg.facts))
        }
    },
    commandHandler = { cmd ->
        when (cmd) {
            is Cmd.Load ->
                dataSource.load()
                    .flatMap(parser::parse)
                    .flatMapIterable { it }
                    .map { it.text }
                    .toList()
                    .map { SideEffect<Msg, News>(Msg.SetFacts(it)) }
                    .observeOn(mainScheduler)
                    .asObservable()
                    .onErrorReturn { SideEffect(Msg.StopLoading, News.Failure) }
        }
    }
) {


    interface Parser {
        fun parse(json: String): Single<List<Fact>>
    }

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
        object Failure : News()
    }


}