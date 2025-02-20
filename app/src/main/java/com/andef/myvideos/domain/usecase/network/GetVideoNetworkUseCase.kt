package com.andef.myvideos.domain.usecase.network

import com.andef.myvideos.domain.entities.Video
import com.andef.myvideos.domain.repository.network.VideoNetworkRepository
import javax.inject.Inject

class GetVideoNetworkUseCase @Inject constructor(
    private val repository: VideoNetworkRepository
) {
    suspend fun execute(id: String): Video {
        return repository.getVideo(id)
    }
}