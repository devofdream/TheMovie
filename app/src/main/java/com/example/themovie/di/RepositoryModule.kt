package com.example.themovie.di

import com.example.themovie.service.MovieService
import com.example.themovie.data.repository.MainRepository
import com.example.themovie.domain.repository.MovieRepository
import com.example.themovie.data.repository.MovieRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepository() : MainRepository {
        return MainRepository()
    }

    @Singleton
    @Provides
    fun provideMovieRepository(movieService: MovieService) : MovieRepository {
        return MovieRepositoryImpl(movieService = movieService)
    }

}