package com.andef.myvideos.data.mapper

import com.andef.myvideos.data.mapper.dto.VideoDTOToVideoMapper
import com.andef.myvideos.data.mapper.dto.VideoItemListHolderDTOToVideoIdListMapper
import com.andef.myvideos.data.network.dto.video.VideoDTO
import com.andef.myvideos.data.network.dto.video.VideoDurationDTO
import com.andef.myvideos.data.network.dto.video.VideoInfoDTO
import com.andef.myvideos.data.network.dto.video.VideoItemDTO
import com.andef.myvideos.data.network.dto.video.VideoThumbnailsDTO
import com.andef.myvideos.data.network.dto.video.VideoUrlDTO
import com.andef.myvideos.data.network.dto.videolist.VideoIdHolderDTO
import com.andef.myvideos.data.network.dto.videolist.VideoItemHolderDTO
import com.andef.myvideos.data.network.dto.videolist.VideoItemListHolderDTO
import junit.framework.TestCase.assertEquals
import org.junit.Test

class DtoMappersTest {
    @Test
    fun `VideoDTO to Video`() {
        val videoUrlDTO = VideoUrlDTO("url")
        val videoThumbnailsDTO = VideoThumbnailsDTO(videoUrlDTO)
        val videoInfoDTO = VideoInfoDTO("title 1", videoThumbnailsDTO)
        val videoDurationDTO = VideoDurationDTO("12")
        val videoDTO = VideoDTO(
            listOf(
                VideoItemDTO("1", videoInfoDTO, videoDurationDTO)
            )
        )
        val video = VideoDTOToVideoMapper.map(videoDTO)
        assertEquals(videoDTO.items[0].id, video.id)
        assertEquals(videoDTO.items[0].info.title, video.title)
        assertEquals(videoDTO.items[0].info.thumbnail.medium.url, video.thumbnailUrl)
        assertEquals(videoDTO.items[0].contentDetails.duration, video.duration)
        assertEquals("$BASE_URL_YOUTUBE${videoDTO.items[0].id}", video.videoUrl)
    }

    @Test
    fun `VideoItemListHolderDTO to VideoIdList`() {
        val videoItemListHolderDTO = VideoItemListHolderDTO(
            "next",
            listOf(
                VideoItemHolderDTO(VideoIdHolderDTO("1")),
                VideoItemHolderDTO(VideoIdHolderDTO("2")),
                VideoItemHolderDTO(VideoIdHolderDTO("3")),
                VideoItemHolderDTO(VideoIdHolderDTO("4"))
            )
        )
        val videoIdList = VideoItemListHolderDTOToVideoIdListMapper.map(videoItemListHolderDTO)
        assertEquals(videoItemListHolderDTO.nextPageToken, videoIdList.nextPageToken)
        assertEquals(videoItemListHolderDTO.videoItems.size, videoIdList.videoIds.size)
        for (i in 0 until videoIdList.videoIds.size) {
            assertEquals(videoItemListHolderDTO.videoItems[i].video.id, videoIdList.videoIds[i])
        }
    }

    companion object {
        private const val BASE_URL_YOUTUBE = "https://www.youtube.com/watch?v="
    }
}