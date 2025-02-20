package com.andef.myvideos.data.network.dto.videolist

import com.google.gson.annotations.SerializedName

//список для получения id всех видео
data class VideoItemListHolderDTO(
    @SerializedName("nextPageToken")
    val nextPageToken: String,
    @SerializedName("items")
    val videoItems: List<VideoItemHolderDTO>
)
