package com.dionep.kotiksmultiplatform.androidApp.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.dionep.kotiksmultiplatform.CatsComponent
import com.dionep.kotiksmultiplatform.CatsView
import com.dionep.kotiksmultiplatform.CatsView.*
import com.dionep.kotiksmultiplatform.androidApp.R
import com.dionep.kotiksmultiplatform.androidApp.databinding.FragmentMainBinding
import com.dionep.kotiksmultiplatform.androidApp.delegates.fact
import com.dionep.kotiksmultiplatform.androidApp.utils.viewBinding
import com.dionep.kotiksmultiplatform.shared.mvi.MviView
import com.rerekt.rekukler.MultiBindingAdapter
import com.rerekt.rekukler.configure

class MainFragment : Fragment(R.layout.fragment_main), MviView<Model, Event> {

    private val viewBinding by viewBinding(FragmentMainBinding::bind)

    private lateinit var component: CatsComponent

    private val factsAdapter by lazy {
        MultiBindingAdapter(
            fact {
                println("Fact clicked")
            }
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component = CatsComponent()
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
        component.onViewCreated(this)
    }

    private fun initRecycler() {
        viewBinding.rvFacts.configure(factsAdapter) {}
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