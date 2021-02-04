package com.dionep.kotiksmultiplatform.androidApp.ui.add

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import com.dionep.kotiksmultiplatform.androidApp.R
import com.dionep.kotiksmultiplatform.androidApp.databinding.FragmentAddFactBinding
import com.dionep.kotiksmultiplatform.androidApp.ui.AppActivity
import com.dionep.kotiksmultiplatform.androidApp.utils.viewBinding
import com.dionep.kotiksmultiplatform.base.MviFragment
import com.dionep.kotiksmultiplatform.features.CreateFactFeatureComponent
import com.dionep.kotiksmultiplatform.features.CreateFactFeatureComponent.*

class AddFactFragment : MviFragment<State, Msg, News>(R.layout.fragment_add_fact) {

    private val viewBinding by viewBinding(FragmentAddFactBinding::bind)

    override val component by lazy { CreateFactFeatureComponent() }

    private val appActivity: AppActivity
        get() = activity as AppActivity

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        with(viewBinding) {
            btnClose.setOnClickListener { onBackPressed() }
            btnAdd.setOnClickListener {
                component.accept(Msg.Create(etFact.text.toString()))
            }
            btnClose.setOnClickListener { appActivity.goBack() }
        }
    }

    override fun renderState(state: State) {
        with(viewBinding) {
            viewLoading.isVisible = state.isLoading
        }
    }

    override fun onBackPressed() {
        appActivity.goBack()
    }

    override fun handleNews(news: News) {
        when (news) {
            is News.Failure -> Toast.makeText(requireContext(), news.message, Toast.LENGTH_SHORT).show()
            is News.CreateSuccess -> onBackPressed()
        }
    }

}