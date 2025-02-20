package com.andef.myvideos.presentation.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andef.myvideos.domain.entities.Video
import com.andef.myvideos.domain.usecase.GetVideoIdListUseCase
import com.andef.myvideos.domain.usecase.GetVideoUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getVideoUseCase: GetVideoUseCase,
    private val getVideoIdListUseCase: GetVideoIdListUseCase
) : ViewModel() {
    private val exceptionHandlerVideosId = CoroutineExceptionHandler { _, e ->
        Log.d(TAG, e.toString())
        _isLoading.value = false
        _error.value = Unit
    }
    private val exceptionHandlerVideo = CoroutineExceptionHandler { _, e ->
        Log.d(TAG, e.toString())
    }

    private val _error = MutableLiveData<Unit>()
    val error: LiveData<Unit>
        get() = _error

    private val _addVideos = MutableLiveData<List<Video>>()
    val addVideos: LiveData<List<Video>>
        get() = _addVideos

    private val _replaceVideos = MutableLiveData<List<Video>>()
    val replaceVideos: LiveData<List<Video>>
        get() = _replaceVideos

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private var nextPageToken: String = ""
    private var lastQuery: String = ""

    private fun loadVideos(isByQuery: Boolean, query: String) {
        viewModelScope.launch(exceptionHandlerVideosId) {
            _isLoading.value = true
            val videos = withContext(Dispatchers.IO) {
                val videosIdList = getVideoIdListUseCase.execute(nextPageToken, query)
                nextPageToken = videosIdList.nextPageToken
                val videosId = videosIdList.videoIds
                videosId.map { id ->
                    async(exceptionHandlerVideo) {
                        getVideoUseCase.execute(id)
                    }
                }.awaitAll()
            }
            if (isByQuery) {
                _replaceVideos.value = videos.toList()
                lastQuery = query
            } else {
                _addVideos.value = videos.toList()
            }
            _isLoading.value = false
        }
    }

    fun loadVideosByLastQuery() {
        loadVideos(false, lastQuery)
    }

    fun loadVideosByQuery(query: String) {
        loadVideos(true, query)
    }

    init {
        loadVideosByLastQuery()
    }

    companion object {
        private const val TAG = "MainViewModel"
    }
}