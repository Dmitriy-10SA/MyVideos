package com.andef.myvideos.di.module

import androidx.lifecycle.ViewModel
import com.andef.myvideos.di.annotation.ViewModelKey
import com.andef.myvideos.presentation.ui.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {
    @Binds
    @ViewModelKey(MainViewModel::class)
    @IntoMap
    fun bindMainViewModel(impl: MainViewModel): ViewModel
}