package com.example.themovie.viewmodels

import androidx.lifecycle.ViewModel
import com.example.themovie.domain.model.Movie
import com.example.themovie.domain.repository.MovieRepository
import com.example.themovie.domain.usecase.GetTopRatedPagerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor() : ViewModel() {
    @Inject lateinit var getTopRatedPagerUseCase: GetTopRatedPagerUseCase

    suspend fun getTopRatedList(): Flow<Movie> {
        return getTopRatedPagerUseCase.getTopRated("ko", 1)
    }
}