package com.andef.myvideos.data.mapper.dto

import com.andef.myvideos.data.network.dto.videolist.VideoItemListHolderDTO
import com.andef.myvideos.domain.entities.VideoIdList

object VideoItemListHolderDTOToVideoIdListMapper {
    fun map(videoItemListHolderDTO: VideoItemListHolderDTO): VideoIdList {
        val videosId = mutableListOf<String>().apply {
            videoItemListHolderDTO.videoItems.forEach {
                add(it.video.id)
            }
        }
        return VideoIdList(
            videoItemListHolderDTO.nextPageToken,
            videosId
        )
    }
}