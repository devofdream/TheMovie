package com.example.themovie.di

import com.example.themovie.network.service.MovieService.MovieService
import com.example.themovie.repository.MainRepository
import com.example.themovie.repository.MovieRepository
import com.example.themovie.repository.MovieRepositoryImpl
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