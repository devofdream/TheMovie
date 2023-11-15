package com.example.themovie.domain.repository

import com.example.themovie.domain.model.Movie
import com.example.themovie.network.response.TopRatedResponse

interface MovieRepository {
    suspend fun getTopRated(language: String, page: Int): List<Movie>
}