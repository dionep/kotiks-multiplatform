package com.dionep.kotiksmultiplatform.androidApp.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.dionep.kotiksmultiplatform.shared.mvi.Component
import com.dionep.kotiksmultiplatform.shared.mvi.MviView

abstract class MviFragment<Model: Any, Event: Any>(@LayoutRes layoutResId: Int) :
    Fragment(layoutResId),
    MviView<Model, Event>
{

    abstract val component: Component<Model, Event, *, *>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        component.onViewCreated(this)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        component.onStart()
    }

    override fun onStop() {
        component.onStop()
        super.onStop()
    }

    override fun onDestroyView() {
        component.onViewDestroyed()
        super.onDestroyView()
    }

    override fun onDestroy() {
        component.onDestroy()
        super.onDestroy()
    }

}