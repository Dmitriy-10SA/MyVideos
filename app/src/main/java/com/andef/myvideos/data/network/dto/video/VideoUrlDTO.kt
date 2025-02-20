package com.andef.myvideos.data.network.dto.video

import com.google.gson.annotations.SerializedName

//url на миниатюру видео (см. VideoThumbnailsDTO)
data class VideoUrlDTO(
    @SerializedName("url")
    val url: String
)
