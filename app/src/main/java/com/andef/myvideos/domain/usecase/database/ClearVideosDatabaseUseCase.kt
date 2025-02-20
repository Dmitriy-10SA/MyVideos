package com.andef.myvideos.domain.usecase.database

import com.andef.myvideos.domain.repository.database.VideoDatabaseRepository
import javax.inject.Inject

class ClearVideosDatabaseUseCase @Inject constructor(
    private val repository: VideoDatabaseRepository
) {
    suspend fun execute() {
        repository.clearVideos()
    }
}