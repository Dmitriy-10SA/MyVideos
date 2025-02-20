package com.andef.myvideos.domain.usecase

import com.andef.myvideos.domain.entities.VideoIdList
import com.andef.myvideos.domain.repository.VideoRepository

class GetVideoIdListUseCase(
    private val repository: VideoRepository
) {
    suspend fun invoke(nextPageToken: String): VideoIdList {
        return repository.getVideoIdList(nextPageToken)
    }
}