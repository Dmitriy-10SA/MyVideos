package com.andef.myvideos.di.module

import android.app.Application
import com.andef.myvideos.data.datasource.dao.VideoDao
import com.andef.myvideos.data.datasource.database.VideoDatabase
import dagger.Module
import dagger.Provides

@Module
class VideoDaoModule {
    @Provides
    fun provideVideoDao(application: Application): VideoDao {
        return VideoDatabase.getInstance(application).dao
    }
}