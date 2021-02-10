package com.bhavik.moviesdb.utils

import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bhavik.moviesdb.data.local.entity.Movie
import com.bhavik.moviesdb.data.remote.Endpoints
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import java.text.SimpleDateFormat

@BindingAdapter("app:imageUrl")
fun setImage(view: ImageView, url: String?) {
    url?.let {
        Glide.with(view.context)
            .load(Endpoints.IMG_BASE_PATH + url)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)
    }
}

@BindingAdapter("bindReleaseDate")
fun bindReleaseDate(view: TextView, movie: Movie) {
    view.text =
        "Release Date : ${SimpleDateFormat("dd MMMM yyyy").format(movie.release_date)}"
}

@BindingAdapter("bindRating")
fun bindRating(ratingBar: RatingBar, movie: Movie) {
    ratingBar.rating = (movie.vote_average / 2).toFloat()
}