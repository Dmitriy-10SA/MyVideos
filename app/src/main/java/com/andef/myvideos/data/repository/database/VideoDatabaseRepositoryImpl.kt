package com.andef.myvideos.data.repository.database

import androidx.lifecycle.LiveData
import com.andef.myvideos.data.datasource.dao.VideoDao
import com.andef.myvideos.data.mapper.dbmodel.VideoDbModelListToVideoListMapper
import com.andef.myvideos.data.mapper.dbmodel.VideoToVideoDbModelMapper
import com.andef.myvideos.domain.entities.Video
import com.andef.myvideos.domain.repository.database.VideoDatabaseRepository
import javax.inject.Inject

class VideoDatabaseRepositoryImpl @Inject constructor(
    private val dao: VideoDao
) : VideoDatabaseRepository {
    override suspend fun addVideo(video: Video) {
        val videoDbModel = VideoToVideoDbModelMapper.map(video)
        dao.addVideo(videoDbModel)
    }

    override suspend fun getInitialVideos(): List<Video> {
        val allVideosDbModel = dao.getInitialVideos()
        val allVideosList = VideoDbModelListToVideoListMapper.map(allVideosDbModel)
        return allVideosList
    }

    override suspend fun getVideosByIds(ids: List<String>): List<Video> {
        val allVideosByIdsDbModel = dao.getVideosByIds(ids)
        val allVideosByIdsList = VideoDbModelListToVideoListMapper.map(allVideosByIdsDbModel)
        return allVideosByIdsList
    }

    override fun getVideoCount(): LiveData<Int> {
        return dao.getVideoCount()
    }

    override suspend fun clearVideos() {
        dao.clearVideos()
    }
}