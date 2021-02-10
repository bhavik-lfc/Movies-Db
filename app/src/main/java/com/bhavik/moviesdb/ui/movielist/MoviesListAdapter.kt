package com.bhavik.moviesdb.ui.movielist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bhavik.moviesdb.R
import com.bhavik.moviesdb.data.local.entity.Movie
import com.bhavik.moviesdb.databinding.RowMovieBinding

class MoviesListAdapter(private val clickListener: (Movie) -> Unit) :
    ListAdapter<Movie, MoviesListAdapter.ViewHolder>(MovieDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        holder.bind(item, clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(private val binding: RowMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Movie, clickListener: (Movie) -> Unit) {

            binding.movie = item
            binding.executePendingBindings()

            binding.root.setOnClickListener {
                clickListener.invoke(item)
            }

        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)

                val view: RowMovieBinding = DataBindingUtil.inflate(
                    layoutInflater,
                    R.layout.row_movie,
                    parent,
                    false
                )

                return ViewHolder(view)
            }
        }
    }


}

class MovieDiffCallback : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
        oldItem == newItem

}