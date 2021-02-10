package com.bhavik.moviesdb.di.module

import androidx.lifecycle.ViewModelProvider
import com.bhavik.moviesdb.data.repository.MovieRepository
import com.bhavik.moviesdb.di.ViewModelProviderFactory
import com.bhavik.moviesdb.ui.base.BaseActivity
import com.bhavik.moviesdb.ui.movielist.MoviesListViewModel
import com.bhavik.moviesdb.utils.NetworkHelper
import com.bhavik.moviesdb.utils.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.processors.PublishProcessor

@Module
class ActivityModule(private val activity: BaseActivity<*, *>) {

    @Provides
    fun provideMoviesListViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        movieRepository: MovieRepository,
        networkHelper: NetworkHelper
    ): MoviesListViewModel = ViewModelProvider(
        activity, ViewModelProviderFactory(MoviesListViewModel::class) {
            MoviesListViewModel(
                schedulerProvider,
                compositeDisposable,
                movieRepository,
                networkHelper
            )
        }).get(MoviesListViewModel::class.java)


}