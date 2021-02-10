package com.bhavik.moviesdb.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bhavik.moviesdb.data.local.dao.MovieDao
import com.bhavik.moviesdb.data.local.entity.Movie
import javax.inject.Singleton

@Singleton
@Database(
    entities = [
        Movie::class
    ],
    exportSchema = false,
    version = 1
)
@TypeConverters(RoomConverters::class)
abstract class DatabaseService : RoomDatabase() {

    abstract fun movieDao(): MovieDao

}