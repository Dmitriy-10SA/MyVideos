package com.andef.myvideos.domain.repository.database

import androidx.lifecycle.LiveData
import com.andef.myvideos.domain.entities.Video

interface VideoDatabaseRepository {
    suspend fun addVideo(video: Video)
    suspend fun getInitialVideos(): List<Video>
    suspend fun getVideosByIds(ids: List<String>): List<Video>
    fun getVideoCount(): LiveData<Int>
    suspend fun clearVideos()
}