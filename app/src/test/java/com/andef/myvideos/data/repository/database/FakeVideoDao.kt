package com.andef.myvideos.data.repository.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.andef.myvideos.data.datasource.dao.VideoDao
import com.andef.myvideos.data.datasource.dbmodel.VideoDbModel

class FakeVideoDao : VideoDao {
    private val videoList = mutableListOf<VideoDbModel>()
    private val videoSize = MutableLiveData(videoList.size)

    override suspend fun addVideo(videoDbModel: VideoDbModel) {
        videoList.add(videoDbModel)
        videoSize.value = videoList.size
    }

    override suspend fun getInitialVideos(): List<VideoDbModel> {
        return videoList.take(20)
    }

    override suspend fun getVideosByIds(ids: List<String>): List<VideoDbModel> {
        return videoList.filter { it.id in ids }
    }

    override fun getVideoCount(): LiveData<Int> {
        return videoSize
    }

    override suspend fun clearVideos() {
        videoList.clear()
        videoSize.value = videoList.size
    }
}