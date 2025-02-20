package com.andef.myvideos.data.repository

import com.andef.myvideos.data.mapper.VideoDTOToVideoMapper
import com.andef.myvideos.data.mapper.VideoItemListHolderDTOToVideoIdListMapper
import com.andef.myvideos.data.network.api.ApiService
import com.andef.myvideos.domain.entities.Video
import com.andef.myvideos.domain.entities.VideoIdList
import com.andef.myvideos.domain.repository.VideoRepository
import javax.inject.Inject

class VideoRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : VideoRepository {
    override suspend fun getVideo(id: String): Video {
        return VideoDTOToVideoMapper.map(apiService.getVideo(id))
    }

    override suspend fun getVideoIdList(nextPageToken: String, query: String): VideoIdList {
        return VideoItemListHolderDTOToVideoIdListMapper.map(
            apiService.getVideoItemList(
                nextPageToken,
                query = query
            )
        )
    }
}