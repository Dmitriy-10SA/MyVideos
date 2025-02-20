package com.andef.myvideos.presentation.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andef.myvideos.domain.entities.Video
import com.andef.myvideos.domain.usecase.database.AddVideoDatabaseUseCase
import com.andef.myvideos.domain.usecase.database.ClearVideosDatabaseUseCase
import com.andef.myvideos.domain.usecase.database.GetInitialVideosDatabaseUseCase
import com.andef.myvideos.domain.usecase.database.GetVideoCountDatabaseUseCase
import com.andef.myvideos.domain.usecase.database.GetVideosByIdsDatabaseUseCase
import com.andef.myvideos.domain.usecase.network.GetVideoIdListNetworkUseCase
import com.andef.myvideos.domain.usecase.network.GetVideoNetworkUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Semaphore
import kotlinx.coroutines.sync.withPermit
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getVideoNetworkUseCase: GetVideoNetworkUseCase,
    private val getVideoIdListNetworkUseCase: GetVideoIdListNetworkUseCase,
    private val addVideoDatabaseUseCase: AddVideoDatabaseUseCase,
    private val clearVideosDatabaseUseCase: ClearVideosDatabaseUseCase,
    private val getInitialVideosDatabaseUseCase: GetInitialVideosDatabaseUseCase,
    private val getVideoCountDatabaseUseCase: GetVideoCountDatabaseUseCase,
    private val getVideosByIdsDatabaseUseCase: GetVideosByIdsDatabaseUseCase
) : ViewModel() {
    private val exceptionHandlerVideosId = CoroutineExceptionHandler { _, e ->
        Log.d(TAG, e.toString())
        _isLoading.value = false
        _error.value = Unit
    }

    private val _error = MutableLiveData<Unit>()
    val error: LiveData<Unit>
        get() = _error

    private val _dataBaseInitialEmpty = MutableLiveData<Unit>()
    val databaseInitialEmpty: LiveData<Unit>
        get() = _dataBaseInitialEmpty

    private val _addVideos = MutableLiveData<List<Video>>()
    val addVideos: LiveData<List<Video>>
        get() = _addVideos

    private val _replaceVideos = MutableLiveData<List<Video>>()
    val replaceVideos: LiveData<List<Video>>
        get() = _replaceVideos

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    val videoCountInDatabase: LiveData<Int>
        get() = getVideoCountDatabaseUseCase.execute()

    private var nextPageToken: String = ""
    private var lastQuery: String = ""

    private val semaphore = Semaphore(MAX_THREAD_COUNT)

    private fun loadVideos(isNeedReplace: Boolean, query: String) {
        viewModelScope.launch(exceptionHandlerVideosId) {
            _isLoading.value = true
            val videos = withContext(Dispatchers.IO) {
                val videosIdList = getVideoIdListNetworkUseCase.execute(nextPageToken, query)

                nextPageToken = videosIdList.nextPageToken

                val videosId = videosIdList.videoIds
                val videosResponse = getVideosByIdsDatabaseUseCase.execute(videosId).toMutableList()

                if (videosResponse.size == videosId.size) {
                    videosResponse
                } else {
                    val idFromVideos = videosResponse.map { it.id }.toHashSet()
                    videosId.map { id ->
                        launch {
                            semaphore.withPermit {
                                runCatching {
                                    if (!idFromVideos.contains(id)) {
                                        val video = getVideoNetworkUseCase.execute(id)
                                        addVideoDatabaseUseCase.execute(video)
                                        videosResponse.add(video)
                                    }
                                }.onFailure { e ->
                                    Log.d(TAG, e.toString())
                                }
                            }
                        }
                    }.joinAll()
                    videosResponse
                }
            }
            if (isNeedReplace) {
                _replaceVideos.value = videos.toList()
                lastQuery = query
            } else {
                _addVideos.value = videos.toList()
            }
            _isLoading.value = false
        }
    }

    fun loadVideosBySwipe() {
        loadVideos(true, lastQuery)
    }

    fun loadVideosByLastQuery() {
        loadVideos(false, lastQuery)
    }

    fun loadVideosByQuery(query: String) {
        loadVideos(true, query)
    }

    fun clearVideos() {
        viewModelScope.launch {
            clearVideosDatabaseUseCase.execute()
        }
    }

    fun loadInitialVideosFromDatabase() {
        viewModelScope.launch {
            _isLoading.value = true
            val videos = getInitialVideosDatabaseUseCase.execute()
            if (videos.isEmpty()) {
                _dataBaseInitialEmpty.value = Unit
            } else {
                _addVideos.value = videos
            }
            _isLoading.value = false
        }
    }

    companion object {
        private const val TAG = "MainViewModel"

        private const val MAX_THREAD_COUNT = 10
    }
}