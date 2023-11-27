package com.example.themovie.data.model

import com.example.themovie.domain.model.Result
import com.example.themovie.network.model.ResultDto

//MovieDto > Movie
fun ResultDto.asDomain() = Result(
    adult = adult,
    backdropPath = backdropPath,
    genreIds = genreIds,
    id = id,
    mediaType = mediaType,
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    overview = overview,
    popularity = popularity,
    posterPath = posterPath,
    releaseDate = releaseDate,
    title = title,
    video = video,
    voteAverage = voteAverage,
    voteCount = voteCount
)
