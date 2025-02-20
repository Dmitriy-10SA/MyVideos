package com.andef.myvideos.data.datasource.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.andef.myvideos.data.datasource.dao.VideoDao
import com.andef.myvideos.data.datasource.dbmodel.VideoDbModel

@Database(entities = [VideoDbModel::class], exportSchema = false, version = 1)
abstract class VideoDatabase : RoomDatabase() {
    abstract val dao: VideoDao

    companion object {
        private const val DB_NAME = "videos.db"
        private var instance: VideoDatabase? = null

        fun getInstance(application: Application): VideoDatabase {
            if (instance != null) {
                return instance!!
            }
            synchronized(this) {
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        application,
                        VideoDatabase::class.java,
                        DB_NAME
                    ).build()
                }
                return instance!!
            }
        }
    }
}