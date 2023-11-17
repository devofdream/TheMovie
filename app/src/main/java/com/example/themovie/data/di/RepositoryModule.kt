package com.example.themovie.data.di

import com.example.themovie.network.MovieApi
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
    fun provideMovieRepository(movieApi: MovieApi) : MovieRepository {
        return MovieRepositoryImpl(movieApi = movieApi)
    }

}