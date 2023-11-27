package com.example.themovie.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.themovie.TheMovieApplication
import com.example.themovie.domain.model.Movie
import com.example.themovie.domain.usecase.GetTopRatedPagerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    application: TheMovieApplication, getTopRatedPagerUseCase: GetTopRatedPagerUseCase
) :
    ViewModel() {

    @Inject
    lateinit var application: TheMovieApplication

    val movieList: Flow<PagingData<Movie>> =
        getTopRatedPagerUseCase.getTopRated(
            viewModelScope,
            language = application.language,
            page = Page
        )

    companion object {
        private const val Page: Int = 20
    }
}