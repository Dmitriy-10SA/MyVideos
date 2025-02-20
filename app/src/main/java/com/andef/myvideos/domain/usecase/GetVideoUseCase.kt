package com.andef.myvideos.domain.usecase

import com.andef.myvideos.domain.entities.Video
import com.andef.myvideos.domain.repository.VideoRepository

class GetVideoUseCase(
    private val repository: VideoRepository
) {
    suspend fun invoke(id: String): Video {
        return repository.getVideo(id)
    }
}