package com.bhavik.moviesdb.utils

import com.bhavik.moviesdb.data.local.entity.Movie
import com.bhavik.moviesdb.data.remote.response.Result
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object Mappers {

    fun convertList(resultList: List<Result>): List<Movie> {

        val movieList = mutableListOf<Movie>()
        resultList.forEach {
            movieList.add(
                Movie(
                    it.id.toLong(),
                    it.title,
                    it.overview,
                    it.poster_path,
                    it.vote_count,
                    it.vote_average,
                    stringToDate(it.release_date),
                    it.adult,
                    it.popularity
                )
            )
        }

        return movieList
    }

    fun stringToDate(date: String): Date = try {
        SimpleDateFormat("yyyy-MM-dd").parse(date)
    } catch (e: ParseException) {
        e.printStackTrace()
        Date()
    }

}