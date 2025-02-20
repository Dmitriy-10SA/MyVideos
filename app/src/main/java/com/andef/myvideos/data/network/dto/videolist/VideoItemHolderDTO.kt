package com.andef.myvideos.data.network.dto.videolist

import com.google.gson.annotations.SerializedName

//хранитель id видео
data class VideoItemHolderDTO(
    @SerializedName("id")
    val video: VideoIdHolderDTO
)