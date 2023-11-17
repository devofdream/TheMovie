package com.example.themovie.domain.repository

import com.example.themovie.domain.model.Movie
import com.example.themovie.network.response.TopRatedResponse
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getTopRated(language: String, page: Int): Flow<List<Movie>>
}