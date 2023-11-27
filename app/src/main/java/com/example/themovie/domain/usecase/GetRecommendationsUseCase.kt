package com.example.themovie.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.themovie.domain.model.Result
import com.example.themovie.domain.repository.MovieRepository
import com.example.themovie.presentation.datasource.RecommendationsListDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class GetRecommendationsUseCase(
    private val movieRepository: MovieRepository
) {
    fun getRecommendations(scope: CoroutineScope, language: String, movieId: Int, page: Int): Flow<PagingData<Result>> {
        return Pager(PagingConfig(pageSize = page)) {
            RecommendationsListDataSource(movieRepository = movieRepository, language = language, movieId = movieId)
        }.flow.cachedIn(scope = scope)

    }
}