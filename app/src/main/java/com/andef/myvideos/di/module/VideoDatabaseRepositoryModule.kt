package com.andef.myvideos.di.module

import com.andef.myvideos.data.repository.database.VideoDatabaseRepositoryImpl
import com.andef.myvideos.domain.repository.database.VideoDatabaseRepository
import dagger.Binds
import dagger.Module

@Module
interface VideoDatabaseRepositoryModule {
    @Binds
    fun bindVideoDatabaseRepository(impl: VideoDatabaseRepositoryImpl): VideoDatabaseRepository
}