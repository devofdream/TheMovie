package com.example.themovie.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themovie.domain.model.Movie
import com.example.themovie.domain.repository.MovieRepository
import com.example.themovie.domain.usecase.GetTopRatedPagerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import org.intellij.lang.annotations.Language
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(getTopRatedPagerUseCase: GetTopRatedPagerUseCase) : ViewModel() {
    val movieList: StateFlow<List<Movie>> = getTopRatedPagerUseCase.getTopRated(Language, Page)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    companion object {
        private const val Language: String = "ko"
        private const val Page: Int = 1
    }
}