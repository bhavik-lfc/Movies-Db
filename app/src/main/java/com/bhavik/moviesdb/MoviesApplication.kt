package com.bhavik.moviesdb

import android.app.Application
import com.bhavik.moviesdb.di.component.ApplicationComponent
import com.bhavik.moviesdb.di.component.DaggerApplicationComponent
import com.bhavik.moviesdb.di.module.ApplicationModule

class MoviesApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        injectDependencies()
    }

    private fun injectDependencies() {
        applicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
        applicationComponent.inject(this)
    }

    fun setComponent(applicationComponent: ApplicationComponent) {
        this.applicationComponent = applicationComponent
    }

}