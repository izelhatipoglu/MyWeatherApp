package com.izelhatipoglu.myweatherapp.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("setImageWithUrl")
fun setImageWithUrl(view: ImageView,url: String? = null){
    url?.let {
        Glide.with(view.context)
        .load("https:$url")
        .centerCrop()
        .into(view)
    }
}