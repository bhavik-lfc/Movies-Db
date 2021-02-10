package com.bhavik.moviesdb.data.local.dao

import androidx.room.*
import com.bhavik.moviesdb.data.local.entity.Movie
import io.reactivex.Single

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie")
    fun getAll(): Single<List<Movie>>

    @Insert
    fun insert(entity: Movie): Single<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: List<Movie>): Single<List<Long>>

    @Delete
    fun delete(entity: Movie): Single<Int>

    @Query("SELECT count(*) from movie")
    fun count(): Single<Int>

}