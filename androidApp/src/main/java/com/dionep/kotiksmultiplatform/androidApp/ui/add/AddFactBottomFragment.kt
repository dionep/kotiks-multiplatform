package com.dionep.kotiksmultiplatform.androidApp.ui.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dionep.kotiksmultiplatform.androidApp.R
import com.dionep.kotiksmultiplatform.androidApp.databinding.FragmentAddFactBottomBinding
import com.dionep.kotiksmultiplatform.androidApp.utils.viewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class AddFactBottomFragment : BottomSheetDialogFragment() {

    private val viewBinding by viewBinding(FragmentAddFactBottomBinding::bind)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_fact_bottom, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        viewBinding.btnClose.setOnClickListener { dismiss() }
    }

}