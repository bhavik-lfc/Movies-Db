package com.bhavik.moviesdb.di.component

import com.bhavik.moviesdb.di.ActivityScope
import com.bhavik.moviesdb.di.module.ActivityModule
import com.bhavik.moviesdb.ui.movielist.MoviesListActivity
import dagger.Component

@ActivityScope
@Component(modules = [ActivityModule::class], dependencies = [ApplicationComponent::class])
interface ActivityComponent {

    fun inject(activity: MoviesListActivity)

}