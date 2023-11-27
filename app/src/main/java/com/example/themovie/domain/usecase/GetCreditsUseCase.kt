package com.example.themovie.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.themovie.domain.model.Cast
import com.example.themovie.domain.repository.MovieRepository
import com.example.themovie.presentation.datasource.CreditsListDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class GetCreditsUseCase(
    private val movieRepository: MovieRepository
) {
    fun getCasts(scope: CoroutineScope, language: String, movieId: Int, page: Int): Flow<PagingData<Cast>> {
        return Pager(PagingConfig(pageSize = page)) {
            CreditsListDataSource(movieRepository = movieRepository, language = language, movieId = movieId)
        }.flow.cachedIn(scope = scope)

    }
}