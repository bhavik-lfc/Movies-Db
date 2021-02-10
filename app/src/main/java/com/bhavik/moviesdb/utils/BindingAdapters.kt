package com.bhavik.moviesdb.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bhavik.moviesdb.data.remote.Endpoints
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

@BindingAdapter("app:imageUrl")
fun setImage(view: ImageView, url: String?) {
    url?.let {
        Glide.with(view.context)
            .load(Endpoints.IMG_BASE_PATH + url)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)
    }
}