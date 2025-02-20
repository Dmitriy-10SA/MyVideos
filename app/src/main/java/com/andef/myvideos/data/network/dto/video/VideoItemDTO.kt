package com.andef.myvideos.data.network.dto.video

import com.google.gson.annotations.SerializedName

//характеристики видео
data class VideoItemDTO(
    @SerializedName("id")
    val id: String,
    @SerializedName("snippet")
    val info: VideoInfoDTO,
    @SerializedName("contentDetails")
    val contentDetails: VideoDurationDTO
)
