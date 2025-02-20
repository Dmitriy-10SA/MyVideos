package com.andef.myvideos.domain.entities

class VideoList {
    var nextPageToken: String? = null

    private val _videos = mutableListOf<Video>()
    val videos
        get() = _videos.toList()
}
