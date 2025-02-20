package com.andef.myvideos.data.datasource.dbmodel

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "videos")
data class VideoDbModel(
    @PrimaryKey
    val id: String,
    val title: String,
    val thumbnailUrl: String,
    val duration: String,
    val videoUrl: String
)