package com.example.themovie.di

import com.example.themovie.domain.repository.MovieRepository
import com.example.themovie.domain.usecase.GetTopRatedPagerUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetTopRatedPagerUseCase(
        movieRepository: MovieRepository,
    ): GetTopRatedPagerUseCase {
        return GetTopRatedPagerUseCase(
            movieRepository
        )
    }

}