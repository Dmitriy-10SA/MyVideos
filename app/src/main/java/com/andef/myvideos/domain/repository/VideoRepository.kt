package com.andef.myvideos.domain.repository

import com.andef.myvideos.domain.entities.Video
import com.andef.myvideos.domain.entities.VideoIdList

interface VideoRepository {
    suspend fun getVideo(id: String): Video
    suspend fun getVideoIdList(nextPageToken: String, query: String): VideoIdList
}