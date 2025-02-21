package com.andef.myvideos.data.repository.network

import com.andef.myvideos.data.network.api.ApiService
import com.andef.myvideos.data.network.dto.video.VideoDTO
import com.andef.myvideos.data.network.dto.video.VideoDurationDTO
import com.andef.myvideos.data.network.dto.video.VideoInfoDTO
import com.andef.myvideos.data.network.dto.video.VideoItemDTO
import com.andef.myvideos.data.network.dto.video.VideoThumbnailsDTO
import com.andef.myvideos.data.network.dto.video.VideoUrlDTO
import com.andef.myvideos.data.network.dto.videolist.VideoIdHolderDTO
import com.andef.myvideos.data.network.dto.videolist.VideoItemHolderDTO
import com.andef.myvideos.data.network.dto.videolist.VideoItemListHolderDTO

class FakeApiService : ApiService {
    override suspend fun getVideoItemList(
        pageToken: String,
        type: String,
        query: String,
        maxResult: Int,
        eventType: String,
        part: String,
        duration: String,
        liveContent: String,
        language: String,
        apiKey: String
    ): VideoItemListHolderDTO {
        return VideoItemListHolderDTO(
            "nextPageToken",
            listOf(
                VideoItemHolderDTO(VideoIdHolderDTO("1")),
                VideoItemHolderDTO(VideoIdHolderDTO("2")),
                VideoItemHolderDTO(VideoIdHolderDTO("3"))
            )
        )
    }

    override suspend fun getVideo(id: String, part: String, apiKey: String): VideoDTO {
        return VideoDTO(
            listOf(
                VideoItemDTO(
                    "id",
                    VideoInfoDTO("title", VideoThumbnailsDTO(VideoUrlDTO("url"))),
                    VideoDurationDTO("duration")
                )
            )
        )
    }
}