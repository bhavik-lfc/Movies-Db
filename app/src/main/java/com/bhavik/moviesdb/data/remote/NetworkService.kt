package com.bhavik.moviesdb.data.remote

import com.bhavik.moviesdb.data.remote.response.MoviesResponse
import io.reactivex.Single
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface NetworkService {

    @GET(Endpoints.POPULAR_MOVIES)
    fun getPopularMovies(): Single<MoviesResponse>

}