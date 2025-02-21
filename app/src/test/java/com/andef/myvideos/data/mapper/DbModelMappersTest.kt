package com.andef.myvideos.data.mapper

import com.andef.myvideos.data.datasource.dbmodel.VideoDbModel
import com.andef.myvideos.data.mapper.dbmodel.VideoDbModelListToVideoListMapper
import com.andef.myvideos.data.mapper.dbmodel.VideoToVideoDbModelMapper
import com.andef.myvideos.domain.entities.Video
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Test

class DbModelMappersTest {
    @Test
    fun `Video to VideoDbModel`() {
        val video = Video(
            "1",
            "title 1",
            "url 1",
            "duration 1",
            "url 2"
        )
        val videoDbModel = VideoToVideoDbModelMapper.map(video)
        assertEquals(video.id, videoDbModel.id)
        assertEquals(video.title, videoDbModel.title)
        assertEquals(video.duration, videoDbModel.duration)
        assertEquals(video.thumbnailUrl, videoDbModel.thumbnailUrl)
        assertEquals(video.videoUrl, videoDbModel.videoUrl)
    }

    @Test
    fun `empty list of VideoDbModel to empty list of Video`() {
        val videoDbModelList = emptyList<VideoDbModel>()
        val videoList = VideoDbModelListToVideoListMapper.map(videoDbModelList)

        assertTrue(videoList.isEmpty())
    }

    @Test
    fun `correct modification of a non-empty list of VideoDbModel to Video`() {
        val videoDbModelList = listOf(
            VideoDbModel(
                "1",
                "title 1",
                "thumbnailUrl 1",
                "duration 1",
                "videoUrl 1"
            ),
            VideoDbModel(
                "2",
                "title 2",
                "thumbnailUrl 2",
                "duration 2",
                "videoUrl 2"
            ),
            VideoDbModel(
                "3",
                "title 3",
                "thumbnailUrl 3",
                "duration 3",
                "videoUrl 3"
            ),
            VideoDbModel(
                "4",
                "title 4",
                "thumbnailUrl 4",
                "duration 4",
                "videoUrl 4"
            )
        )
        val videoList = VideoDbModelListToVideoListMapper.map(videoDbModelList)

        assertEquals(videoDbModelList.size, videoList.size)
        for (i in videoList.indices) {
            assertEquals(videoDbModelList[i].id, videoList[i].id)
            assertEquals(videoDbModelList[i].title, videoList[i].title)
            assertEquals(videoDbModelList[i].duration, videoList[i].duration)
            assertEquals(videoDbModelList[i].thumbnailUrl, videoList[i].thumbnailUrl)
            assertEquals(videoDbModelList[i].videoUrl, videoList[i].videoUrl)
        }
    }
}