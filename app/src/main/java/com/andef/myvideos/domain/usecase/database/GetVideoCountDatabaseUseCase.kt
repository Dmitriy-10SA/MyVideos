package com.andef.myvideos.domain.usecase.database

import androidx.lifecycle.LiveData
import com.andef.myvideos.domain.repository.database.VideoDatabaseRepository
import javax.inject.Inject

class GetVideoCountDatabaseUseCase @Inject constructor(
    private val repository: VideoDatabaseRepository
) {
    fun execute(): LiveData<Int> {
        return repository.getVideoCount()
    }
}