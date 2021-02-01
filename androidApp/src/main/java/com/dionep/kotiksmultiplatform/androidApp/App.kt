package com.dionep.kotiksmultiplatform.androidApp

import android.app.Application
import com.dionep.kotiksmultiplatform.base.initKoin

class App : Application() {

    override fun onCreate() {
        initKoin()
        super.onCreate()
    }

}