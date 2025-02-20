package com.andef.myvideos.data.network.dto.video

import com.google.gson.annotations.SerializedName

data class VideoDTO(
    @SerializedName("items")
    val items: List<VideoItemDTO>
)
