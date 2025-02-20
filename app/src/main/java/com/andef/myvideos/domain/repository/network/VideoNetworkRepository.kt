package com.andef.myvideos.domain.repository.network

import com.andef.myvideos.domain.entities.Video
import com.andef.myvideos.domain.entities.VideoIdList

interface VideoNetworkRepository {
    suspend fun getVideo(id: String): Video
    suspend fun getVideoIdList(nextPageToken: String, query: String): VideoIdList
}