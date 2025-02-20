package com.andef.myvideos.domain.usecase.database

import com.andef.myvideos.domain.entities.Video
import com.andef.myvideos.domain.repository.database.VideoDatabaseRepository
import javax.inject.Inject

class GetInitialVideosDatabaseUseCase @Inject constructor(
    private val repository: VideoDatabaseRepository
) {
    suspend fun execute(): List<Video> {
        return repository.getInitialVideos()
    }
}