package com.andef.myvideos.data.network.dto.video

import com.google.gson.annotations.SerializedName

data class VideoDurationDTO(
    @SerializedName("duration")
    val duration: String
)
