package com.andef.myvideos.data.repository.network

import com.andef.myvideos.domain.entities.Video
import com.andef.myvideos.domain.entities.VideoIdList
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Test

class VideoNetworkRepositoryImplTest {
    private val executor =
        java.util.concurrent.Executors.newCachedThreadPool().asCoroutineDispatcher()
    private val scope = CoroutineScope(executor)

    private lateinit var repository: VideoNetworkRepositoryImpl
    private lateinit var fakeApiService: FakeApiService

    @Before
    fun init() {
        fakeApiService = FakeApiService()
        repository = VideoNetworkRepositoryImpl(fakeApiService)
    }

    @Test
    fun `get video fun`() {
        scope.launch {
            val expectedVideo = Video(
                "id",
                "title",
                "url",
                "duration",
                "${BASE_URL_YOUTUBE}id"
            )
            val actualVideo = repository.getVideo("id")
            assertEquals(expectedVideo, actualVideo)
        }
    }

    @Test
    fun `get video id list fun`() {
        scope.launch {
            val expectedVideoIdList = VideoIdList(
                "nextPageToken",
                listOf("1", "2", "3")
            )
            val actualVideoIdList = repository.getVideoIdList("", "")
            assertEquals(expectedVideoIdList.nextPageToken, actualVideoIdList.nextPageToken)
            assertEquals(expectedVideoIdList.videoIds.size, actualVideoIdList.videoIds.size)
            for (i in 0 until actualVideoIdList.videoIds.size) {
                assertEquals(expectedVideoIdList.videoIds[i], actualVideoIdList.videoIds[i])
            }
        }
    }

    companion object {
        private const val BASE_URL_YOUTUBE = "https://www.youtube.com/watch?v="
    }
}