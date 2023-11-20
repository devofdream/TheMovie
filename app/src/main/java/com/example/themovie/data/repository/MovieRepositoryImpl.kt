package com.example.themovie.data.repository

import com.example.themovie.BuildConfig
import com.example.themovie.domain.repository.MovieRepository
import com.example.themovie.network.MovieApi
import com.example.themovie.network.response.TopRatedResponse

class MovieRepositoryImpl(private val movieApi: MovieApi) : MovieRepository {
    private val apiKey = BuildConfig.API_KEY
    override suspend fun getTopRated(language: String, page: Int): TopRatedResponse = movieApi.topRated(api_key = apiKey, language = language, page = page)
}