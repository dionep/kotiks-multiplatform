package com.dionep.kotiksmultiplatform.androidApp.ui

import com.dionep.kotiksmultiplatform.CatsView
import com.dionep.kotiksmultiplatform.CatsView.Event
import com.dionep.kotiksmultiplatform.CatsView.Model
import com.dionep.kotiksmultiplatform.androidApp.R
import com.dionep.kotiksmultiplatform.androidApp.databinding.FragmentMainBinding
import com.dionep.kotiksmultiplatform.shared.mvi.AbstractMviView
import com.google.android.material.snackbar.Snackbar

internal class CatsViewImpl(
    root: FragmentMainBinding,
    private val onItemsUpdate: (List<String>) -> Unit
) : AbstractMviView<Model, Event>(), CatsView {

    private val swipeRefreshLayout = root.srlFacts
    private val snackbar = Snackbar.make(root.root, R.string.error, Snackbar.LENGTH_INDEFINITE)

    init {
        swipeRefreshLayout.setOnRefreshListener {
            accept(Event.Load)
        }
    }

    override fun render(model: Model) {
        swipeRefreshLayout.isRefreshing = model.isLoading
        onItemsUpdate.invoke(model.catsFacts)
        println(model)
        if (model.isError) {
            snackbar.show()
        } else {
            snackbar.dismiss()
        }
    }
}
