package com.example.themovie.data.repository

import com.example.themovie.BuildConfig
import com.example.themovie.data.model.asDomain
import com.example.themovie.domain.model.Movie
import com.example.themovie.domain.repository.MovieRepository
import com.example.themovie.network.MovieApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieRepositoryImpl(private val movieApi: MovieApi) : MovieRepository {
    private val apiKey = BuildConfig.API_KEY
    override fun getTopRated(language: String, page: Int): Flow<List<Movie>> = flow {
        val topRatedResponse = movieApi.topRated(api_key = apiKey, language = language, page = page)

        val list = topRatedResponse.results.map {it.asDomain()}
        emit(list)
    }
}