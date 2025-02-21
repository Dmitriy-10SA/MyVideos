package com.andef.myvideos.data.database

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.andef.myvideos.data.datasource.dao.VideoDao
import com.andef.myvideos.data.datasource.database.VideoDatabase
import com.andef.myvideos.data.mapper.dbmodel.VideoDbModelListToVideoListMapper
import com.andef.myvideos.data.mapper.dbmodel.VideoToVideoDbModelMapper
import com.andef.myvideos.domain.entities.Video
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.launch
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.Executors

@RunWith(AndroidJUnit4::class)
class VideoDaoTest {
    private val executor = Executors.newCachedThreadPool().asCoroutineDispatcher()
    private val scope = CoroutineScope(executor)

    private lateinit var database: VideoDatabase
    private lateinit var videoDao: VideoDao

    @Before
    fun init() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            VideoDatabase::class.java
        ).allowMainThreadQueries().build()

        videoDao = database.dao
    }

    @After
    fun teardown() {
        database.close()
    }

    private fun receiveVideo(id: Int): Video {
        return Video(
            "$id",
            "title $id",
            "url 1 $id",
            "duration $id",
            "url 2 $id"
        )
    }

    @Test
    fun add_videos_and_initial_videos_check() {
        scope.launch {
            val video1 = receiveVideo(1)
            val video2 = receiveVideo(2)
            val video3 = receiveVideo(3)
            val video4 = receiveVideo(4)
            videoDao.addVideo(VideoToVideoDbModelMapper.map(video1))
            videoDao.addVideo(VideoToVideoDbModelMapper.map(video2))
            videoDao.addVideo(VideoToVideoDbModelMapper.map(video3))
            videoDao.addVideo(VideoToVideoDbModelMapper.map(video4))
            val videosIdsInitialVideosFun = videoDao.getInitialVideos()
            assertEquals(4, videosIdsInitialVideosFun.size)
            val expectedSetOfIds = setOf(video1.id, video2.id, video3.id, video4.id)
            val actualSetOfIds = videosIdsInitialVideosFun.toSet()
            assertEquals(expectedSetOfIds, actualSetOfIds)
        }
    }

    @Test
    fun get_videos_by_id_check() {
        scope.launch {
            val video1 = receiveVideo(1)
            val video2 = receiveVideo(2)
            val video3 = receiveVideo(3)
            val video4 = receiveVideo(4)
            videoDao.addVideo(VideoToVideoDbModelMapper.map(video1))
            videoDao.addVideo(VideoToVideoDbModelMapper.map(video2))
            videoDao.addVideo(VideoToVideoDbModelMapper.map(video3))
            videoDao.addVideo(VideoToVideoDbModelMapper.map(video4))
            val videosDbModelId = videoDao.getVideosByIds(listOf("2", "3", "5", "7"))
            assertEquals(2, videosDbModelId.size)
            val videosId = VideoDbModelListToVideoListMapper.map(videosDbModelId)
            assertEquals(setOf(video2, video3), setOf(videosId))
        }
    }


    @Test
    fun get_video_count_check_and_clear_db_check() {
        scope.launch {
            val video1 = receiveVideo(1)
            val video2 = receiveVideo(2)
            val video3 = receiveVideo(3)
            val video4 = receiveVideo(4)
            videoDao.addVideo(VideoToVideoDbModelMapper.map(video1))
            videoDao.addVideo(VideoToVideoDbModelMapper.map(video2))
            assertEquals(2, videoDao.getVideoCount().value)
            videoDao.addVideo(VideoToVideoDbModelMapper.map(video3))
            videoDao.addVideo(VideoToVideoDbModelMapper.map(video4))
            assertEquals(4, videoDao.getVideoCount().value)
            videoDao.clearVideos()
            assertEquals(0, videoDao.getVideoCount().value)
        }
    }
}