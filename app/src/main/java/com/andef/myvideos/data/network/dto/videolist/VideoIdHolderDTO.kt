package com.andef.myvideos.data.network.dto.videolist

import com.google.gson.annotations.SerializedName

//id видео
data class VideoIdHolderDTO(
    @SerializedName("videoId")
    val id: String
)