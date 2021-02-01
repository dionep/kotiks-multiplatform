package com.dionep.kotiksmultiplatform.androidApp.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.dionep.kotiksmultiplatform.base.MviComponent

abstract class MviFragment<State, Msg: Any, News>(@LayoutRes layoutResId: Int) :
    Fragment(layoutResId)
{

    abstract val component: MviComponent<State, Msg, News>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        component.bindListeners(
            stateListener = ::renderState,
            newsListener = ::handleNews
        )
    }

    abstract fun renderState(state: State)
    abstract fun handleNews(news: News)

    override fun onDestroyView() {
        component.dispose()
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
        component.dispose()
    }

}