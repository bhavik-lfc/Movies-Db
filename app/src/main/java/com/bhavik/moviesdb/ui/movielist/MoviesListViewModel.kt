package com.bhavik.moviesdb.ui.movielist

import androidx.lifecycle.MutableLiveData
import com.bhavik.moviesdb.data.local.entity.Movie
import com.bhavik.moviesdb.data.repository.MovieRepository
import com.bhavik.moviesdb.ui.base.BaseViewModel
import com.bhavik.moviesdb.utils.Logger
import com.bhavik.moviesdb.utils.Mappers
import com.bhavik.moviesdb.utils.NetworkHelper
import com.bhavik.moviesdb.utils.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class MoviesListViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    val movieRepository: MovieRepository,
    networkHelper: NetworkHelper
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {

    val loading: MutableLiveData<Boolean> = MutableLiveData()
    val movieList: MutableLiveData<List<Movie>> = MutableLiveData()

    init {
        compositeDisposable.add(
            movieRepository.fetchPopularMovies()
                .map { result ->
                    return@map Mappers.convertList(result)
                }.flatMap { movies ->
                    movieRepository.saveMovies(movies)
                }
                .doFinally {
                    loading.postValue(false)
                }
                .subscribeOn(schedulerProvider.io())
                .subscribe({
                    getMovies()
                }, {
                })
        )
    }

    fun getMovies() {
        compositeDisposable.add(
            movieRepository.getPopularMovies()
                .doFinally {
                    loading.postValue(false)
                }
                .subscribeOn(schedulerProvider.io())
                .subscribe({
                    movieList.postValue(it)
                }, {})
        )
    }

    override fun onCreate() {
        getMovies()
    }


}