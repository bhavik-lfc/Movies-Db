package com.bhavik.moviesdb.di.module

import androidx.room.Room
import com.bhavik.moviesdb.BuildConfig
import com.bhavik.moviesdb.MoviesApplication
import com.bhavik.moviesdb.data.local.DatabaseService
import com.bhavik.moviesdb.data.remote.NetworkService
import com.bhavik.moviesdb.data.remote.Networking
import com.bhavik.moviesdb.utils.NetworkHelper
import com.bhavik.moviesdb.utils.RxSchedulerProvider
import com.bhavik.moviesdb.utils.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
class ApplicationModule(val application: MoviesApplication) {

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    fun provideSchedulerProvider(): SchedulerProvider = RxSchedulerProvider()

    @Provides
    @Singleton
    fun provideNetworkService(): NetworkService =
        Networking.create(
            BuildConfig.BASE_URL,
            application.cacheDir,
            10 * 1024 * 1024 // 10MB
        )

    @Singleton
    @Provides
    fun provideNetworkHelper(): NetworkHelper = NetworkHelper(application)

    @Provides
    @Singleton
    fun provideDatabaseService(): DatabaseService =
        Room.databaseBuilder(
            application, DatabaseService::class.java,
            "movies-db"
        ).build()


}