package com.andef.myvideos.domain.entities

data class VideoIdList(
    val nextPageToken: String,
    val videoIds: List<String>
)
