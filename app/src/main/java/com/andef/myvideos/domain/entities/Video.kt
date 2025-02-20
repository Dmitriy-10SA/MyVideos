package com.andef.myvideos.domain.entities

data class Video(
    val id: String,
    val title: String,
    val thumbnailUrl: String,
    val duration: String,
    val videoUrl: String
)
