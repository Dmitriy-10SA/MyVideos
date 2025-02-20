package com.andef.myvideos.data.mapper.dbmodel

import com.andef.myvideos.data.datasource.dbmodel.VideoDbModel
import com.andef.myvideos.domain.entities.Video

object VideoToVideoDbModelMapper {
    fun map(video: Video): VideoDbModel {
        return VideoDbModel(
            video.id,
            video.title,
            video.thumbnailUrl,
            video.duration,
            video.videoUrl
        )
    }
}