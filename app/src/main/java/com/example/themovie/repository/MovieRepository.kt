package com.example.themovie.repository

import com.example.themovie.network.response.TopRatedResponse

interface MovieRepository {
    suspend fun getTopRated(language: String, page: Int): TopRatedResponse
}