package com.example.themovie.domain.usecase

import com.example.themovie.domain.model.Movie
import com.example.themovie.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow

class GetTopRatedPagerUseCase(private val movieRepository: MovieRepository): UseCase {
    suspend fun getTopRated(language: String, page: Int) : Flow<Movie> {
        val list = movieRepository.getTopRated(language, page)
        return list.asFlow()
    }
}