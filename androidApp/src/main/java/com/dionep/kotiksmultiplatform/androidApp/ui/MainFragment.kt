package com.dionep.kotiksmultiplatform.androidApp.ui

import android.os.Bundle
import android.view.View
import com.dionep.kotiksmultiplatform.CatsComponent
import com.dionep.kotiksmultiplatform.CatsView.*
import com.dionep.kotiksmultiplatform.androidApp.R
import com.dionep.kotiksmultiplatform.androidApp.base.MviFragment
import com.dionep.kotiksmultiplatform.androidApp.databinding.FragmentMainBinding
import com.dionep.kotiksmultiplatform.androidApp.delegates.fact
import com.dionep.kotiksmultiplatform.androidApp.utils.viewBinding
import com.rerekt.rekukler.MultiBindingAdapter
import com.rerekt.rekukler.configure

class MainFragment : MviFragment<UiState, UiNews, UiEvents>(R.layout.fragment_main) {

    private val viewBinding by viewBinding(FragmentMainBinding::bind)

    override val component by lazy { CatsComponent() }

    private val factsAdapter by lazy {
        MultiBindingAdapter(
            fact {
                println("Fact clicked")
            }
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        viewBinding.srlFacts.setOnRefreshListener {
            component.accept(UiEvents.Load)
        }
    }

    private fun initRecycler() {
        viewBinding.rvFacts.configure(factsAdapter) {}
    }

    override fun render(state: UiState) {
        factsAdapter.items = state.facts
        viewBinding.srlFacts.isRefreshing = state.isLoading
    }

    override fun handleNews(news: UiNews) {
        println(news)
    }

}