package com.dionep.kotiksmultiplatform.androidApp.delegates

import com.dionep.kotiksmultiplatform.androidApp.R
import com.dionep.kotiksmultiplatform.androidApp.databinding.ItemFactBinding
import com.dionep.kotiksmultiplatform.shared.model.Fact
import com.rerekt.rekukler.viewBinder

fun fact(
    onDeleteClicked: (Int) -> Unit
) = viewBinder<Fact, ItemFactBinding>(
    layoutResId = R.layout.item_fact,
    binder = ItemFactBinding::bind
) {
    bindView {
        btnDelete.setOnClickListener { _ -> onDeleteClicked.invoke(it.id) }
        tvFact.text = it.text
    }
}