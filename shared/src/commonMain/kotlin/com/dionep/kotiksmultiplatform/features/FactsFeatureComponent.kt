package com.dionep.kotiksmultiplatform.features

import com.badoo.reaktive.observable.map
import com.badoo.reaktive.observable.onErrorReturn
import com.badoo.reaktive.observable.toList
import com.badoo.reaktive.scheduler.mainScheduler
import com.badoo.reaktive.single.*
import com.dionep.kotiksmultiplatform.features.FactsFeatureComponent.*
import com.dionep.kotiksmultiplatform.base.MviComponent
import com.dionep.kotiksmultiplatform.datasource.CatsDataSource
import com.dionep.kotiksmultiplatform.datasource.CatsDataSourceFactory
import com.dionep.kotiksmultiplatform.integration.CatsStoreParser
import com.dionep.kotiksmultiplatform.integration.Parser
import com.dionep.kotiksmultiplatform.shared.mvi.*

class FactsFeatureComponent internal constructor(dataSource: CatsDataSource)
    : MviComponent<State, Msg, News>() {

    constructor() : this(CatsDataSourceFactory())

    private val parser: Parser = CatsStoreParser

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
        object Failure : News()
    }

}