package com.example.themovie.viewmodels

import androidx.lifecycle.ViewModel
import com.example.themovie.data.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepository,
) : ViewModel() {
    val name = repository.getName()
}