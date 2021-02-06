package com.dionep.kotiksmultiplatform.features

import com.badoo.reaktive.utils.ensureNeverFrozen
import com.badoo.reaktive.observable.onErrorReturn
import com.badoo.reaktive.scheduler.ioScheduler
import com.badoo.reaktive.scheduler.mainScheduler
import com.badoo.reaktive.single.asObservable
import com.badoo.reaktive.single.map
import com.badoo.reaktive.single.observeOn
import com.badoo.reaktive.single.subscribeOn
import com.dionep.kotiksmultiplatform.base.MviComponent
import com.dionep.kotiksmultiplatform.features.CreateFactFeatureComponent.*
import com.dionep.kotiksmultiplatform.features.changes.Changes
import com.dionep.kotiksmultiplatform.repository.FactsRepository
import com.dionep.kotiksmultiplatform.shared.mvi.Feature
import com.dionep.kotiksmultiplatform.shared.mvi.SideEffect
import com.dionep.kotiksmultiplatform.shared.mvi.Update
import org.koin.core.*

class CreateFactFeatureComponent : MviComponent<State, Msg, News>(), KoinComponent {

    private val factsRepository by inject<FactsRepository>()
    private val changes by inject<Changes>()

    override val feature = Feature<State, Cmd, Msg, News>(
        initialState = State(),
        reducer = { msg, state ->
            when (msg) {
                is Msg.Create -> Update(state.copy(isLoading = true), Cmd.Create(msg.text))
                is Msg.StopLoading -> Update(state.copy(isLoading = false))
                is Msg.CreaseSuccess -> {
                    changes.onFactAdded()
                    Update()
                }
            }
        },
        commandHandler = { cmd ->
            when (cmd) {
                is Cmd.Create ->
                    factsRepository.createFact(cmd.text)
                        .map {
                            SideEffect<Msg, News>(Msg.StopLoading, News.CreateSuccess)
                        }
                        .subscribeOn(ioScheduler)
                        .observeOn(mainScheduler)
                        .asObservable()
                        .onErrorReturn {
                            println("Occured error: $it")
                            SideEffect(Msg.StopLoading, News.Failure(it.message ?: "Errrrror"))
                        }
            }
        },
        newsListener = { newsListener.invoke(it) },
        stateListener = { stateListener.invoke(it) }
    )

    data class State(
        val isLoading: Boolean = false
    )

    sealed class Msg {
        data class Create(val text: String) : Msg()
        object StopLoading : Msg()
        object CreaseSuccess : Msg()
    }

    sealed class Cmd {
        data class Create(val text: String) : Cmd()
    }

    sealed class News {
        data class Failure(val message: String) : News()
        object CreateSuccess : News()
    }

}