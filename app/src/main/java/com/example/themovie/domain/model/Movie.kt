package com.example.themovie.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.text.SimpleDateFormat

@Parcelize
data class Movie(
    val id: Int,
    val poster_path: String?,
    val adult: Boolean,
    val overview: String,
    val release_date: String?,
    val genre_ids: List<Int>,
    val original_title: String,
    val original_language: String?,
    val title: String?,
    val backdrop_path: String?,
    val popularity: Float,
    val vote_count: Int,
    val video: Boolean,
    val vote_average: Float,
) : Parcelable

val formatter = SimpleDateFormat("yyyy-mm-dd")
val formatter2 = SimpleDateFormat("m월 dd, yyyy")
val Movie.posterDate: String
    get() = formatter2.format(formatter.parse(release_date))
