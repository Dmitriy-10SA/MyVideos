package com.andef.myvideos.data.repository.network

import com.andef.myvideos.data.mapper.dto.VideoDTOToVideoMapper
import com.andef.myvideos.data.mapper.dto.VideoItemListHolderDTOToVideoIdListMapper
import com.andef.myvideos.data.network.api.ApiService
import com.andef.myvideos.domain.entities.Video
import com.andef.myvideos.domain.entities.VideoIdList
import com.andef.myvideos.domain.repository.network.VideoNetworkRepository
import javax.inject.Inject

class VideoNetworkRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : VideoNetworkRepository {
    override suspend fun getVideo(id: String): Video {
        val videoDTO = apiService.getVideo(id)
        return VideoDTOToVideoMapper.map(videoDTO)
    }

    override suspend fun getVideoIdList(nextPageToken: String, query: String): VideoIdList {
        val videoItemList = apiService.getVideoItemList(nextPageToken, query = query)
        return VideoItemListHolderDTOToVideoIdListMapper.map(videoItemList)
    }
}