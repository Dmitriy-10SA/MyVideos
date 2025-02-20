package com.andef.myvideos.data.mapper

import com.andef.myvideos.data.network.dto.video.VideoDTO
import com.andef.myvideos.domain.entities.Video

object VideoDTOToVideoMapper {
    fun map(videoDTO: VideoDTO): Video {
        val videoDTOItem = videoDTO.items[0]
        return Video(
            videoDTOItem.id,
            videoDTOItem.info.title,
            videoDTOItem.info.thumbnail.medium.url,
            videoDTOItem.contentDetails.duration,
            "$URL_FOR_VIDEO${videoDTOItem.id}"
        )
    }

    private const val URL_FOR_VIDEO = "https://www.youtube.com/watch?v="
}