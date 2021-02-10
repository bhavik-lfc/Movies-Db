package com.bhavik.moviesdb.ui.movielist

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.bhavik.moviesdb.R
import com.bhavik.moviesdb.data.local.MovieSort
import com.bhavik.moviesdb.databinding.ActivityMoviesListBinding
import com.bhavik.moviesdb.di.component.ActivityComponent
import com.bhavik.moviesdb.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_movies_list.*


class MoviesListActivity : BaseActivity<ActivityMoviesListBinding, MoviesListViewModel>() {

    override fun provideLayoutId(): Int = R.layout.activity_movies_list

    private lateinit var moviesListAdapter: MoviesListAdapter

    var sortList: ArrayList<String> = arrayListOf()

    override fun setupView(savedInstanceState: Bundle?) {
        setRecyclerView()
        setSpinner()
    }

    private fun setSpinner() {
        sortList.add("Sort By")

        sortList.addAll(
            MovieSort.values().map {
                it.sort
            }
        )

        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
            this,
            R.layout.dropdown_menu_popup_item,
            sortList
        )

        filled_exposed_dropdown.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    if (position != 0)
                        viewModel.setSortOption(MovieSort.valueOfOrDefault(sortList[position]))

                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }

            }

        filled_exposed_dropdown.adapter = adapter
    }

    private fun setRecyclerView() {
        moviesListAdapter = MoviesListAdapter {
            Toast.makeText(this@MoviesListActivity, it.toString(), Toast.LENGTH_LONG).show()
        }

        rvMovies.apply {
            itemAnimator = null
            rvMovies.layoutManager = GridLayoutManager(this@MoviesListActivity, 2)
            rvMovies.adapter = moviesListAdapter
        }
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
                moviesListAdapter.submitList(this) {
                    rvMovies.scrollToPosition(0)
                }
            }
        })

        viewModel.sortStatus.observe(this, {
            it?.run {
                viewModel.sortBy(this)
            }
        })

    }


}