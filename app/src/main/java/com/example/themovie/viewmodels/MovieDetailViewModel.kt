package com.example.themovie.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.themovie.TheMovieApplication
import com.example.themovie.domain.model.Cast
import com.example.themovie.domain.model.Result
import com.example.themovie.domain.usecase.GetCreditsUseCase
import com.example.themovie.domain.usecase.GetRecommendationsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    application: TheMovieApplication, getCreditsUseCase: GetCreditsUseCase, getRecommendationsUseCase: GetRecommendationsUseCase
) :
    ViewModel() {

    val castsList: Flow<PagingData<Cast>> =
        getCreditsUseCase.getCasts(
            viewModelScope,
            language = application.language,
            page = Page,
            movieId = application.selectedMovie!!.id
        )

    val recommendationsList: Flow<PagingData<Result>> =
        getRecommendationsUseCase.getRecommendations(
            viewModelScope,
            language = application.language,
            page = Page,
            movieId = application.selectedMovie!!.id
        )

    companion object {
        private const val Page: Int = 20
    }
}