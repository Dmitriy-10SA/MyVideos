package com.andef.myvideos.di.module

import com.andef.myvideos.data.network.api.ApiFactory
import com.andef.myvideos.data.network.api.ApiService
import dagger.Module
import dagger.Provides

@Module
class ApiServiceModule {
    @Provides
    fun provideApiService(): ApiService = ApiFactory.apiService
}