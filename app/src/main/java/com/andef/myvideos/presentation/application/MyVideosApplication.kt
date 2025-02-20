package com.andef.myvideos.presentation.application

import android.app.Application
import com.andef.myvideos.di.component.DaggerMyVideosComponent

class MyVideosApplication : Application() {
    val component by lazy {
        DaggerMyVideosComponent.factory().create(this)
    }
}