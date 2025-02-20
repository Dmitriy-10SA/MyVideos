package com.andef.myvideos.di.module

import com.andef.myvideos.data.repository.network.VideoNetworkRepositoryImpl
import com.andef.myvideos.domain.repository.network.VideoNetworkRepository
import dagger.Binds
import dagger.Module

@Module
interface VideoNetworkRepositoryModule {
    @Binds
    fun bindVideoRepository(impl: VideoNetworkRepositoryImpl): VideoNetworkRepository
}