package com.andef.myvideos.di.module

import com.andef.myvideos.data.repository.VideoRepositoryImpl
import com.andef.myvideos.domain.repository.VideoRepository
import dagger.Binds
import dagger.Module

@Module
interface VideoRepositoryModule {
    @Binds
    fun bindVideoRepository(impl: VideoRepositoryImpl): VideoRepository
}