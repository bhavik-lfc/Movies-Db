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

    @Query("SELECT * FROM movie ORDER BY title ASC")
    fun getMovieAscending(): Single<List<Movie>>

    @Query("SELECT * FROM movie ORDER BY title DESC")
    fun getMovieDescending(): Single<List<Movie>>

    @Query("SELECT * FROM movie ORDER BY release_date ASC")
    fun getReleaseAscending(): Single<List<Movie>>

    @Query("SELECT * FROM movie ORDER BY release_date DESC")
    fun getReleaseDescending(): Single<List<Movie>>

    @Query("SELECT * FROM movie ORDER BY popularity ASC")
    fun getPopularityAscending(): Single<List<Movie>>

    @Query("SELECT * FROM movie ORDER BY popularity DESC")
    fun getPopularityDescending(): Single<List<Movie>>

}