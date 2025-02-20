package com.andef.myvideos.domain.usecase

import com.andef.myvideos.domain.entities.VideoIdList
import com.andef.myvideos.domain.repository.VideoRepository
import retrofit2.http.Query
import javax.inject.Inject

class GetVideoIdListUseCase @Inject constructor(
    private val repository: VideoRepository
) {
    suspend fun execute(nextPageToken: String, query: String): VideoIdList {
        return repository.getVideoIdList(nextPageToken, query)
    }
}