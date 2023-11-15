package com.example.themovie.data.repository

import com.example.themovie.BuildConfig
import com.example.themovie.domain.model.Movie
import com.example.themovie.domain.repository.MovieRepository
import com.example.themovie.network.response.TopRatedResponse
import com.example.themovie.service.MovieService

class MovieRepositoryImpl(private val movieService: MovieService) : MovieRepository {
    private val apiKey = BuildConfig.API_KEY
    override suspend fun getTopRated(language: String, page: Int): List<Movie> {
        val topRatedResponse = movieService.topRated(api_key = apiKey, language = language, page = page)

        val list = mutableListOf<Movie>()
        for(result in topRatedResponse.results) {
            list.add(
                Movie(
                    id = result.id,
                    poster_path = result.posterPath,
                    adult = result.adult,
                    overview = result.overview,
                    release_date = result.releaseDate,
                    genre_ids = result.genreIds,
                    original_title = result.originalTitle,
                    original_language = result.originalLanguage,
                    title = result.title,
                    backdrop_path = result.backdropPath,
                    popularity = result.popularity.toFloat(),
                    vote_count = result.voteCount,
                    video = result.video,
                    vote_average = result.voteAverage.toFloat()
                )
            )
        }

        return list

    }


}