package com.example.themovie.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.themovie.domain.model.Movie
import com.example.themovie.domain.usecase.GetTopRatedPagerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(getTopRatedPagerUseCase: GetTopRatedPagerUseCase) : ViewModel() {
    val movieList: Flow<PagingData<Movie>> = getTopRatedPagerUseCase.getTopRated(viewModelScope, language = Language, page = Page)

    companion object {
        private const val Language: String = "ko"
        private const val Page: Int = 20
    }
}