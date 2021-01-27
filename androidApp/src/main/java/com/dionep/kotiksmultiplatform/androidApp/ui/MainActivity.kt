package com.dionep.kotiksmultiplatform.androidApp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dionep.kotiksmultiplatform.androidApp.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .add(R.id.container_main, MainFragment())
            .commitAllowingStateLoss()
    }

}
