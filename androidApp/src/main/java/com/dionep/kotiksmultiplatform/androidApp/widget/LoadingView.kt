package com.dionep.kotiksmultiplatform.androidApp.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.dionep.kotiksmultiplatform.androidApp.databinding.LayoutLoadingBinding

class LoadingView @JvmOverloads constructor(
    context: Context,
    attributesSet: AttributeSet? = null,
) : FrameLayout(context, attributesSet) {

    private val viewBinding = LayoutLoadingBinding.inflate(LayoutInflater.from(context), this, true)

}