package com.andef.myvideos.data.mapper.dbmodel

import com.andef.myvideos.data.datasource.dbmodel.VideoDbModel
import com.andef.myvideos.domain.entities.Video

object VideoDbModelListToVideoListMapper {
    fun map(videoDbModelList: List<VideoDbModel>): List<Video> {
        return videoDbModelList.map { videoDbModel ->
            Video(
                videoDbModel.id,
                videoDbModel.title,
                videoDbModel.thumbnailUrl,
                videoDbModel.duration,
                videoDbModel.videoUrl
            )
        }
    }
}