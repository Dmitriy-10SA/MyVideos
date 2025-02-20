package com.andef.myvideos.domain.usecase.network

import com.andef.myvideos.domain.entities.VideoIdList
import com.andef.myvideos.domain.repository.network.VideoNetworkRepository
import javax.inject.Inject

class GetVideoIdListNetworkUseCase @Inject constructor(
    private val repository: VideoNetworkRepository
) {
    suspend fun execute(nextPageToken: String, query: String): VideoIdList {
        return repository.getVideoIdList(nextPageToken, query)
    }
}