package com.example.themovie.domain.usecase

import com.example.themovie.domain.model.Movie
import com.example.themovie.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetTopRatedPagerUseCase(private val movieRepository: MovieRepository) {
    fun getTopRated(language: String, page: Int) : Flow<List<Movie>> = movieRepository.getTopRated(language, page)
}