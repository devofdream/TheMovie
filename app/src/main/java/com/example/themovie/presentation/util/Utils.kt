package com.example.themovie.presentation.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.themovie.R


fun ImageView.loadImage(url: String) {

    val base = "https://image.tmdb.org/t/p/w500"

    Glide.with(this)
        .load(base + url)
        .placeholder(R.drawable.baseline_question_mark)
        .into(this)
}

