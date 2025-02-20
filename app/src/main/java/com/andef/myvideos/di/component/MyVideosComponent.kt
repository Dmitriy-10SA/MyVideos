package com.andef.myvideos.di.component

import android.app.Application
import com.andef.myvideos.di.annotation.ApplicationScope
import com.andef.myvideos.di.module.ApiServiceModule
import com.andef.myvideos.di.module.VideoDaoModule
import com.andef.myvideos.di.module.VideoDatabaseRepositoryModule
import com.andef.myvideos.di.module.VideoNetworkRepositoryModule
import com.andef.myvideos.di.module.ViewModelModule
import com.andef.myvideos.presentation.ui.MainActivity
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        VideoNetworkRepositoryModule::class,
        ApiServiceModule::class,
        ViewModelModule::class,
        VideoDatabaseRepositoryModule::class,
        VideoDaoModule::class
    ]
)
interface MyVideosComponent {
    fun inject(activity: MainActivity)

    @Component.Factory
    interface MyVideosFactory {
        fun create(@BindsInstance application: Application): MyVideosComponent
    }
}