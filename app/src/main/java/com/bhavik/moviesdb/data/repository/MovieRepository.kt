package com.bhavik.moviesdb.data.repository

import com.bhavik.moviesdb.data.local.DatabaseService
import com.bhavik.moviesdb.data.local.entity.Movie
import com.bhavik.moviesdb.data.remote.NetworkService
import com.bhavik.moviesdb.data.remote.response.Result
import io.reactivex.Single
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val networkService: NetworkService,
    private val databaseService: DatabaseService
) {

    fun fetchPopularMovies(): Single<List<Result>> = networkService.getPopularMovies().map {
        it.results
    }

    fun getPopularMovies(): Single<List<Movie>> = databaseService.movieDao().getAll()


    fun saveMovies(movies: List<Movie>): Single<List<Long>> =
        databaseService.movieDao().insert(movies)


}