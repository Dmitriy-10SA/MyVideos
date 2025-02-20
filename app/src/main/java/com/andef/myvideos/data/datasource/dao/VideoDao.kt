package com.andef.myvideos.data.datasource.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.andef.myvideos.data.datasource.dbmodel.VideoDbModel

@Dao
interface VideoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addVideo(videoDbModel: VideoDbModel)

    @Query("SELECT * FROM videos LIMIT 20")
    suspend fun getInitialVideos(): List<VideoDbModel>

    @Query("SELECT * FROM videos WHERE id IN (:ids)")
    suspend fun getVideosByIds(ids: List<String>): List<VideoDbModel>

    @Query("SELECT COUNT(*) FROM videos")
    fun getVideoCount(): LiveData<Int>

    @Query("DELETE FROM videos")
    suspend fun clearVideos()
}