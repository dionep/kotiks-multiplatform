package com.dionep.kotiksmultiplatform.androidApp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.commitNow
import com.dionep.kotiksmultiplatform.androidApp.R
import com.dionep.kotiksmultiplatform.base.MviFragment

class AppActivity : AppCompatActivity() {

    private val currentFragment
        get() = supportFragmentManager.findFragmentById(R.id.container) as? MviFragment<*, *, *>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        goTo(FactsFragment())
    }

    fun goTo(fragment: Fragment) {
        supportFragmentManager.commit {
            replace(R.id.container_main, fragment)
            addToBackStack(fragment.hashCode().toString())
        }
    }

    fun goBack() {
        supportFragmentManager.popBackStack()
    }

    override fun onBackPressed() {
        currentFragment?.onBackPressed() ?: super.onBackPressed()
    }
}
