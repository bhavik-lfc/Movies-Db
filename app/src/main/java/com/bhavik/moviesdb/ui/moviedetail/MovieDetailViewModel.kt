package com.bhavik.moviesdb.ui.moviedetail

import com.bhavik.moviesdb.ui.base.BaseViewModel
import com.bhavik.moviesdb.utils.NetworkHelper
import com.bhavik.moviesdb.utils.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class MovieDetailViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {

    override fun onCreate() {

    }


}