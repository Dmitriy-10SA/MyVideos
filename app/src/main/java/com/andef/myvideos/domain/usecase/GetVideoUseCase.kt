package com.andef.myvideos.domain.usecase

import com.andef.myvideos.domain.entities.Video
import com.andef.myvideos.domain.repository.VideoRepository
import javax.inject.Inject

class GetVideoUseCase @Inject constructor(
    private val repository: VideoRepository
) {
    suspend fun execute(id: String): Video {
        return repository.getVideo(id)
    }
}