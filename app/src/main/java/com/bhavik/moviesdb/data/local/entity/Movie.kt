package com.bhavik.moviesdb.data.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import org.jetbrains.annotations.NotNull
import java.util.*

@Parcelize
@Entity(tableName = "movie")
data class Movie(

    @PrimaryKey(autoGenerate = true)
    @NotNull
    val id: Long,

    @ColumnInfo(name = "title")
    @NotNull
    val title: String,

    @ColumnInfo(name = "overview")
    @NotNull
    val overview: String,

    @ColumnInfo(name = "poster_path")
    @NotNull
    val poster_path: String,

    @ColumnInfo(name = "vote_count")
    @NotNull
    val vote_count: Int,

    @ColumnInfo(name = "vote_average")
    @NotNull
    val vote_average: Double,

    @ColumnInfo(name = "release_date")
    @NotNull
    val release_date: Date,

    @ColumnInfo(name = "adult")
    @NotNull
    val adult: Boolean,

    @ColumnInfo(name = "popularity")
    @NotNull
    val popularity: Double

) : Parcelable