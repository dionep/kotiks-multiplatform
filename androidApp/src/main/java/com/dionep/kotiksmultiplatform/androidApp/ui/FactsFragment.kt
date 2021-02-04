package com.dionep.kotiksmultiplatform.androidApp.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.dionep.kotiksmultiplatform.features.FactsFeatureComponent
import com.dionep.kotiksmultiplatform.features.FactsFeatureComponent.*
import com.dionep.kotiksmultiplatform.androidApp.R
import com.dionep.kotiksmultiplatform.base.MviFragment
import com.dionep.kotiksmultiplatform.androidApp.databinding.FragmentMainBinding
import com.dionep.kotiksmultiplatform.androidApp.delegates.fact
import com.dionep.kotiksmultiplatform.androidApp.ui.add.AddFactBottomFragment
import com.dionep.kotiksmultiplatform.androidApp.utils.viewBinding
import com.rerekt.rekukler.MultiBindingAdapter
import com.rerekt.rekukler.configure

class FactsFragment : MviFragment<State, Msg, News>(R.layout.fragment_main) {

    private val viewBinding by viewBinding(FragmentMainBinding::bind)

    override val component by lazy { FactsFeatureComponent() }

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
        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        viewBinding.apply {
            srlFacts.setOnRefreshListener { component.accept(Msg.Load) }
            fabAddFact.setOnClickListener { AddFactBottomFragment().show(childFragmentManager, null) }
        }
    }

    private fun initRecycler() {
        viewBinding.rvFacts.configure(factsAdapter) {}
    }

    override fun renderState(state: State) {
        factsAdapter.items = state.facts
        viewBinding.srlFacts.isRefreshing = state.isLoading
    }

    override fun handleNews(news: News) {
        when (news) {
            is News.Failure -> {
                println(news.throwable)
                Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
            }
        }
    }

}