package com.dionep.kotiksmultiplatform.androidApp.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.dionep.kotiksmultiplatform.CatsComponent
import com.dionep.kotiksmultiplatform.CatsView
import com.dionep.kotiksmultiplatform.CatsView.*
import com.dionep.kotiksmultiplatform.androidApp.R
import com.dionep.kotiksmultiplatform.androidApp.base.MviFragment
import com.dionep.kotiksmultiplatform.androidApp.databinding.FragmentMainBinding
import com.dionep.kotiksmultiplatform.androidApp.delegates.fact
import com.dionep.kotiksmultiplatform.androidApp.utils.viewBinding
import com.dionep.kotiksmultiplatform.shared.mvi.MviView
import com.rerekt.rekukler.MultiBindingAdapter
import com.rerekt.rekukler.configure

class MainFragment : MviFragment<Model, Event>(R.layout.fragment_main) {

    private val viewBinding by viewBinding(FragmentMainBinding::bind)

    override val component by lazy { CatsComponent() }

    private val factsAdapter by lazy {
        MultiBindingAdapter(
            fact {
                println("Fact clicked")
            }
        )
    }

    override fun render(model: Model) {
        factsAdapter.items = model.catsFacts
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        viewBinding.srlFacts.setOnRefreshListener {
            component.accept(Event.Load)
        }
    }

    private fun initRecycler() {
        viewBinding.rvFacts.configure(factsAdapter) {}
    }

}