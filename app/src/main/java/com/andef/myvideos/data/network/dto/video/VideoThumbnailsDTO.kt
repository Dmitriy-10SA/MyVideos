package com.andef.myvideos.data.network.dto.video

import com.google.gson.annotations.SerializedName

//миниатюра видео (см. VideoInfoDTO)
data class VideoThumbnailsDTO(
    @SerializedName("medium")
    val medium: VideoUrlDTO
)
