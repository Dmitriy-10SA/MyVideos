package com.andef.myvideos.presentation.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andef.myvideos.domain.entities.Video
import com.andef.myvideos.domain.usecase.GetVideoIdListUseCase
import com.andef.myvideos.domain.usecase.GetVideoUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getVideoUseCase: GetVideoUseCase,
    private val getVideoIdListUseCase: GetVideoIdListUseCase
) : ViewModel() {
    private val _videos = MutableLiveData<List<Video>>()
    val videos: LiveData<List<Video>>
        get() = _videos

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    fun getVideos(nextPageToken: String = "") {
        viewModelScope.launch {
            _isLoading.value = true
            val videos = withContext(Dispatchers.IO) {
                val videosId = getVideoIdListUseCase.execute(nextPageToken).videoIds
                mutableListOf<Video>().apply {
                    videosId.forEach {
                        val video = getVideoUseCase.execute(it)
                        add(video)
                    }
                }
            }.toList()
            _videos.value = videos
            _isLoading.value = false
        }

    }
}