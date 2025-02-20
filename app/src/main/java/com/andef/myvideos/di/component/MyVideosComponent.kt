package com.andef.myvideos.di.component

import com.andef.myvideos.di.annotation.ApplicationScope
import com.andef.myvideos.di.module.ApiServiceModule
import com.andef.myvideos.di.module.VideoRepositoryModule
import com.andef.myvideos.di.module.ViewModelModule
import com.andef.myvideos.presentation.ui.MainActivity
import dagger.Component

@ApplicationScope
@Component(
    modules = [VideoRepositoryModule::class, ApiServiceModule::class, ViewModelModule::class]
)
interface MyVideosComponent {
    fun inject(activity: MainActivity)
}