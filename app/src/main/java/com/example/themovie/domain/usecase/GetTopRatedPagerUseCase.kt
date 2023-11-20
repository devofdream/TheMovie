package com.example.themovie.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.themovie.domain.model.Movie
import com.example.themovie.domain.repository.MovieRepository
import com.example.themovie.presentation.datasource.TopRatedListDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
class GetTopRatedPagerUseCase(
    private val movieRepository: MovieRepository
) {
    fun getTopRated(scope: CoroutineScope, language: String, page: Int): Flow<PagingData<Movie>> {
        //movieRepository.getTopRated(language, page)
        return Pager(PagingConfig(pageSize = page)) {
            TopRatedListDataSource(movieRepository = movieRepository, language = language)
        }.flow.cachedIn(scope = scope)

    }
}