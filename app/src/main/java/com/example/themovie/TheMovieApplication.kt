package com.example.themovie

import android.app.Application
import com.example.themovie.domain.model.Movie
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TheMovieApplication : Application() {
    var language = "ko"
    var selectedMovie : Movie? = null
}