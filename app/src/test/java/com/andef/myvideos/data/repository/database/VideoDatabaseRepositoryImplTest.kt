package com.andef.myvideos.data.repository.database

import com.andef.myvideos.data.mapper.dbmodel.VideoToVideoDbModelMapper
import com.andef.myvideos.domain.entities.Video
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Test
import java.util.concurrent.Executors

class VideoDatabaseRepositoryImplTest {
    private val executor = Executors.newCachedThreadPool().asCoroutineDispatcher()
    private val scope = CoroutineScope(executor)

    private lateinit var repository: VideoDatabaseRepositoryImpl
    private lateinit var fakeDao: FakeVideoDao

    @Before
    fun init() {
        fakeDao = FakeVideoDao()
        repository = VideoDatabaseRepositoryImpl(fakeDao)
    }

    private suspend fun initFunForAddTwoVideoInDB(): Pair<Video, Video> {
        val video1 = Video(
            "1",
            "Video 1",
            "url1",
            "100",
            "video_url1"
        )
        val video2 = Video(
            "2",
            "Video 2",
            "url2",
            "200",
            "video_url2"
        )
        repository.addVideo(video1)
        repository.addVideo(video2)
        return video1 to video2
    }

    @Test
    fun `get initial videos fun`() {
        scope.launch {
            val pair = initFunForAddTwoVideoInDB()
            val video1 = pair.first
            val video2 = pair.second
            val initialVideos = fakeDao.getInitialVideos()
            assertEquals(2, initialVideos.size)
            assertEquals(VideoToVideoDbModelMapper.map(video1), initialVideos[0])
            assertEquals(VideoToVideoDbModelMapper.map(video2), initialVideos[1])
        }
    }

    @Test
    fun `get videos by ids fun`() {
        scope.launch {
            val pair = initFunForAddTwoVideoInDB()
            val video1 = pair.first
            val video2 = pair.second
            val videosIds = repository.getVideosByIds(listOf(video1.id, video2.id, "7", "5"))
            assertEquals(2, videosIds.size)
            assertEquals(setOf(video1.id, video2.id), setOf(videosIds[0], videosIds[2]))
        }
    }

    @Test
    fun `add video saves video to database and check get video count`() {
        scope.launch {
            initFunForAddTwoVideoInDB()
            val videosFromDB = fakeDao.getVideoCount()
            assertEquals(2, videosFromDB.value)
        }
    }

    @Test
    fun `clear videos remove all videos`() {
        scope.launch {
            initFunForAddTwoVideoInDB()
            assertEquals(2, repository.getVideoCount().value)
            assertEquals(2, repository.getInitialVideos().size)
            repository.clearVideos()
            assertEquals(0, repository.getVideoCount().value)
            assertEquals(0, repository.getVideoCount().value)
        }
    }
}