package com.dionep.kotiksmultiplatform.androidApp.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.commitNow
import androidx.fragment.app.transaction
import com.dionep.kotiksmultiplatform.features.FactsFeatureComponent
import com.dionep.kotiksmultiplatform.features.FactsFeatureComponent.*
import com.dionep.kotiksmultiplatform.androidApp.R
import com.dionep.kotiksmultiplatform.base.MviFragment
import com.dionep.kotiksmultiplatform.androidApp.databinding.FragmentMainBinding
import com.dionep.kotiksmultiplatform.androidApp.delegates.fact
import com.dionep.kotiksmultiplatform.androidApp.ui.add.AddFactFragment
import com.dionep.kotiksmultiplatform.androidApp.utils.viewBinding
import com.rerekt.rekukler.MultiBindingAdapter
import com.rerekt.rekukler.configure

class FactsFragment : MviFragment<State, Msg, News>(R.layout.fragment_main) {

    private val viewBinding by viewBinding(FragmentMainBinding::bind)

    override val component by lazy { FactsFeatureComponent() }

    private val appActivity: AppActivity
        get() = activity as AppActivity

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
        println("onViewCreated")
    }

    private fun setOnClickListeners() {
        with(viewBinding) {
            srlFacts.setOnRefreshListener { component.accept(Msg.Load) }
            fabAddFact.setOnClickListener {
                appActivity.goTo(AddFactFragment())
            }
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