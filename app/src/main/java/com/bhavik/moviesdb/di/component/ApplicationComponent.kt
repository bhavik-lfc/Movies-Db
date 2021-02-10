package com.bhavik.moviesdb.di.component

import com.bhavik.moviesdb.MoviesApplication
import com.bhavik.moviesdb.data.local.DatabaseService
import com.bhavik.moviesdb.data.remote.NetworkService
import com.bhavik.moviesdb.data.repository.MovieRepository
import com.bhavik.moviesdb.di.module.ApplicationModule
import com.bhavik.moviesdb.utils.NetworkHelper
import com.bhavik.moviesdb.utils.SchedulerProvider
import dagger.Component
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(app: MoviesApplication)

    fun getNetworkService(): NetworkService

    fun getDatabaseService(): DatabaseService

    fun getSchedulerProvider(): SchedulerProvider

    fun getCompositeDisposable(): CompositeDisposable

    fun getMovieRepository(): MovieRepository

    fun getNetworkHelper(): NetworkHelper

}