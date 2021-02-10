package com.bhavik.moviesdb.ui.moviedetail

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import com.bhavik.moviesdb.R
import com.bhavik.moviesdb.data.local.entity.Movie
import com.bhavik.moviesdb.databinding.ActivityMovieDetailBinding
import com.bhavik.moviesdb.di.component.ActivityComponent
import com.bhavik.moviesdb.ui.base.BaseActivity
import com.bhavik.moviesdb.utils.Constants.MOVIE


class MovieDetailActivity : BaseActivity<ActivityMovieDetailBinding, MovieDetailViewModel>() {

    override fun provideLayoutId(): Int = R.layout.activity_movie_detail

    override fun setupView(savedInstanceState: Bundle?) {

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = intent.getParcelableExtra<Movie>(MOVIE)?.title
    }

    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun setViewModel(binding: ActivityMovieDetailBinding) {
        binding.movie = intent.getParcelableExtra(MOVIE)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}