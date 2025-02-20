package com.andef.myvideos.data.network.dto.video

import com.google.gson.annotations.SerializedName

data class VideoInfoDTO(
    @SerializedName("title")
    val title: String,
    @SerializedName("thumbnails")
    val thumbnail: VideoThumbnailsDTO
)
