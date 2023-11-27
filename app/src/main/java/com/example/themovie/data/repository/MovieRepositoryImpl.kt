package com.example.themovie.data.repository

import com.example.themovie.BuildConfig
import com.example.themovie.domain.repository.MovieRepository
import com.example.themovie.network.MovieApi
import com.example.themovie.network.response.CreditsResponse
import com.example.themovie.network.response.RecommendationsResponse
import com.example.themovie.network.response.TopRatedResponse

class MovieRepositoryImpl(private val movieApi: MovieApi) : MovieRepository {
    private val apiKey = BuildConfig.API_KEY
    override suspend fun getTopRated(language: String, page: Int): TopRatedResponse =
        movieApi.topRated(api_key = apiKey, language = language, page = page)

    override suspend fun getCredits(movieId: Int, language: String): CreditsResponse =
        movieApi.getCredits(api_key = apiKey, movieId = movieId, language = language)


    override suspend fun getRecommendations(
        movieId: Int,
        language: String
    ): RecommendationsResponse =
        movieApi.getRecommendations(api_key = apiKey, movieId = movieId, language = language)

}