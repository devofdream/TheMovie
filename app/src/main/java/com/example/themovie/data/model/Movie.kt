package com.example.themovie.data.model

import com.example.themovie.domain.model.Movie
import com.example.themovie.network.model.MovieDto

//MovieDto > Movie
fun MovieDto.asDomain() = Movie(
    id = id,
    poster_path = posterPath,
    adult = adult,
    overview = overview,
    release_date = releaseDate,
    genre_ids = genreIds,
    original_title = originalTitle,
    original_language = originalLanguage,
    title = title,
    backdrop_path = backdropPath,
    popularity = popularity.toFloat(),
    vote_count = voteCount,
    video = video,
    vote_average = voteAverage.toFloat()
)