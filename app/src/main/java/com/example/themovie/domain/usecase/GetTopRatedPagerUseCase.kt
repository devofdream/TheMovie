package com.example.themovie.domain.usecase

import com.example.themovie.domain.model.Movie
import com.example.themovie.domain.repository.MovieRepository
import com.example.themovie.mapper.MovieDtoMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow

class GetTopRatedPagerUseCase(
    private val movieRepository: MovieRepository
) {
    fun getTopRated(language: String, page: Int): Flow<List<Movie>>  = movieRepository.getTopRated(language, page)
}