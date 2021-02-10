package com.bhavik.moviesdb.ui.movielist

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.bhavik.moviesdb.R
import com.bhavik.moviesdb.databinding.ActivityMoviesListBinding
import com.bhavik.moviesdb.di.component.ActivityComponent
import com.bhavik.moviesdb.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_movies_list.*

class MoviesListActivity : BaseActivity<ActivityMoviesListBinding, MoviesListViewModel>() {

    override fun provideLayoutId(): Int = R.layout.activity_movies_list

    private lateinit var moviesListAdapter: MoviesListAdapter

    override fun setupView(savedInstanceState: Bundle?) {
        moviesListAdapter = MoviesListAdapter {
            Toast.makeText(this@MoviesListActivity, it.toString(), Toast.LENGTH_LONG).show()
        }
        rvMovies.layoutManager = GridLayoutManager(this, 2)
        rvMovies.adapter = moviesListAdapter

    }

    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun setViewModel(binding: ActivityMoviesListBinding) {
        binding.viewModel = viewModel
    }

    override fun setupObservers() {
        super.setupObservers()

        viewModel.loading.observe(this, {
            progressBar.visibility = if (it) View.VISIBLE else View.GONE
        })

        viewModel.movieList.observe(this, {
            it?.run {
                moviesListAdapter.submitList(this)
            }
        })

    }


}