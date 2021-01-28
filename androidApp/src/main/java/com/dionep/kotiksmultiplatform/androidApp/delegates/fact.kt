package com.dionep.kotiksmultiplatform.androidApp.delegates

import com.dionep.kotiksmultiplatform.androidApp.R
import com.dionep.kotiksmultiplatform.androidApp.databinding.ItemFactBinding
import com.rerekt.rekukler.viewBinder

fun fact(
    onClick: () -> Unit
) = viewBinder<String, ItemFactBinding>(
    layoutResId = R.layout.item_fact,
    binder = ItemFactBinding::bind
) {
    bindView {
        tvFact.text = it
    }
}