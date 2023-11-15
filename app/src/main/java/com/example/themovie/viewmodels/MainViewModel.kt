package com.example.themovie.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.themovie.network.response.TopRatedResponse
import com.example.themovie.repository.MainRepository
import com.example.themovie.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepository,
    private val movieRepository: MovieRepository
) : ViewModel() {
    val name = repository.getName()

    suspend fun getMovies() {
        val movieListResponse: TopRatedResponse = movieRepository.getTopRated("ko", 1)
        Log.d("yeomhk", movieListResponse.toString())
    }

}