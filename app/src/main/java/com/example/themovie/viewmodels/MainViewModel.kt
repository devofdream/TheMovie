package com.example.themovie.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.themovie.network.response.TopRatedResponse
import com.example.themovie.data.repository.MainRepository
import com.example.themovie.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepository,
) : ViewModel() {
    val name = repository.getName()
}