package com.dionep.kotiksmultiplatform.shared.features

import com.badoo.reaktive.observable.observable
import com.dionep.kotiksmultiplatform.shared.mvi.Feature
import com.dionep.kotiksmultiplatform.shared.mvi.SideEffect
import com.dionep.kotiksmultiplatform.shared.mvi.Update

fun mainFeature(
    stateListener: (State) -> Unit,
    newsListener: (News) -> Unit
) = Feature<State, Cmd, Msg, News>(
    initialState = State(),
    initialMessages = setOf(Msg.GetItems),
    reducer = { msg, state ->
        when (msg) {
            is Msg.GetItems -> Update(
                state.copy(
                    isLoading = true,
                    isErrorVisible = false
                )
            )
            is Msg.SetItems -> Update(
                state.copy(
                    isLoading = false,
                    isErrorVisible = false,
                    items = msg.list
                )
            )
            is Msg.Error -> Update(
                state.copy(
                    isLoading = false,
                    isErrorVisible = false
                )
            )
        }
    },
    commandHandler = { cmd ->
         when (cmd) {
             is Cmd.GetItems -> observable {
                 val randomNum = (0..1).random()
                 it.onNext(
                     SideEffect(
                         msg = if (randomNum == 0) Msg.Error else Msg.SetItems((1..30).map { it.toString() }),
                         news = if (randomNum == 0) News.Failure("Error!") else null
                     )
                 )
             }
         }
    },
    stateListener = {},
    newsListener = {}
)

data class State(
    val isLoading: Boolean = true,
    val isErrorVisible: Boolean = false,
    val items: List<String> = listOf()
)

sealed class Cmd {
    object GetItems : Cmd()
}

sealed class Msg {
    object GetItems : Msg()
    data class SetItems(val list: List<String>) : Msg()
    object Error : Msg()
}

sealed class News {
    data class Failure(val message: String) : News()
}