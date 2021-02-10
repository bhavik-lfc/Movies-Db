package com.bhavik.moviesdb.data.local

import androidx.room.TypeConverter
import java.util.*

object RoomConverters {

    @TypeConverter
    @JvmStatic
    fun toTime(value: Long) = Date(value)

    @TypeConverter
    @JvmStatic
    fun fromTime(value: Date) = value.time

}