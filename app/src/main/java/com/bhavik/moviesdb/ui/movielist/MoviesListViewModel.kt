package com.bhavik.moviesdb.ui.movielist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.bhavik.moviesdb.data.local.MovieSort
import com.bhavik.moviesdb.data.local.entity.Movie
import com.bhavik.moviesdb.data.repository.MovieRepository
import com.bhavik.moviesdb.ui.base.BaseViewModel
import com.bhavik.moviesdb.utils.Logger
import com.bhavik.moviesdb.utils.Mappers
import com.bhavik.moviesdb.utils.NetworkHelper
import com.bhavik.moviesdb.utils.SchedulerProvider
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

class MoviesListViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    val movieRepository: MovieRepository,
    networkHelper: NetworkHelper
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {

    val progressLoading: MutableLiveData<Boolean> = MutableLiveData(true)
    val movieList: MutableLiveData<List<Movie>> = MutableLiveData()
    val sortStatus: MutableLiveData<MovieSort> = MutableLiveData()
    val errorStatus: MutableLiveData<Boolean> = MutableLiveData(false)
    val sortVisibility = Transformations.map(movieList) {
        it.isNotEmpty()
    }

    init {
        compositeDisposable.add(
            movieRepository.fetchPopularMovies()
                .map { result ->
                    return@map Mappers.convertList(result)
                }.flatMap { movies ->
                    movieRepository.saveMovies(movies)
                }
                .subscribeOn(schedulerProvider.io())
                .subscribe({
                    progressLoading.postValue(false)
                    getMovies()
                }, {
                    progressLoading.postValue(false)
                    if (movieList.value.isNullOrEmpty()) {
                        errorStatus.postValue(true)
                    }
                })
        )
    }

    private fun getMovies() {
        compositeDisposable.add(
            movieRepository.getPopularMovies()
                .subscribeOn(schedulerProvider.io())
                .subscribe({
                    if (it.isNotEmpty()) {
                        progressLoading.postValue(false)
                        movieList.postValue(it)
                    }
                }, {

                })
        )
    }

    fun setSortOption(sortType: MovieSort) {
        sortStatus.postValue(sortType)
    }

    fun sortBy(sortType: MovieSort) {
        compositeDisposable.add(
            getSortBySingle(sortType)
                .subscribeOn(schedulerProvider.io())
                .subscribe({
                    if (it.isNotEmpty()) {
                        movieList.postValue(it)
                    }
                }, {

                })
        )

    }

    private fun getSortBySingle(sortType: MovieSort): Single<List<Movie>> =
        when (sortType) {
            MovieSort.TITLE_ASC -> movieRepository.getMovieAscending()
            MovieSort.TITLE_DSC -> movieRepository.getMovieDescending()
            MovieSort.RELEASE_ASC -> movieRepository.getReleaseAscending()
            MovieSort.RELEASE_DSC -> movieRepository.getReleaseDescending()
            MovieSort.POPULARITY_ASC -> movieRepository.getPopularityAscending()
            MovieSort.POPULARITY_DSC -> movieRepository.getPopularityDescending()
        }


    override fun onCreate() {
        getMovies()
    }


}