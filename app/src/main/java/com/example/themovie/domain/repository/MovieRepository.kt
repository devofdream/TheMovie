package com.example.themovie.domain.repository

import com.example.themovie.network.response.CreditsResponse
import com.example.themovie.network.response.RecommendationsResponse
import com.example.themovie.network.response.TopRatedResponse

interface MovieRepository {
    suspend fun getTopRated(language: String, page: Int): TopRatedResponse
    suspend fun getCredits(movieId: Int, language: String): CreditsResponse
    suspend fun getRecommendations(movieId: Int, language: String): RecommendationsResponse
}