package com.example.themovie.repository

import com.example.themovie.BuildConfig
import com.example.themovie.network.response.TopRatedResponse
import com.example.themovie.network.service.MovieService.MovieService

class MovieRepositoryImpl(private val movieService: MovieService) : MovieRepository {
    private val apiKey = BuildConfig.API_KEY
    override suspend fun getTopRated(language: String, page: Int): TopRatedResponse {
        return movieService.topRated(api_key = apiKey, language = language, page = page)
    }


}