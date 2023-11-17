package com.example.themovie.mapper

import com.example.themovie.domain.model.Movie
import com.example.themovie.network.model.MovieDto

class MovieDtoMapper : DomainMapper<MovieDto, Movie> {
    override fun mapToDomainModel(model: MovieDto): Movie {
        return Movie(
            poster_path = model.posterPath,
            adult = model.adult,
            overview = model.overview,
            release_date = model.releaseDate,
            genre_ids = model.genreIds,
            id = model.id,
            original_title = model.originalTitle,
            original_language = model.originalLanguage,
            title = model.title,
            backdrop_path = model.backdropPath,
            popularity = model.popularity.toFloat(),
            vote_count = model.voteCount,
            video = model.video,
            vote_average = model.voteAverage.toFloat()
        )
    }

    override fun mapFromDomainModel(domainModel: Movie): MovieDto {
        return MovieDto(
            posterPath = domainModel.poster_path!!,
            adult = domainModel.adult,
            overview = domainModel.overview,
            releaseDate = domainModel.release_date!!,
            genreIds = domainModel.genre_ids,
            id = domainModel.id,
            originalTitle = domainModel.original_title,
            originalLanguage = domainModel.original_language!!,
            title = domainModel.title!!,
            backdropPath = domainModel.backdrop_path!!,
            popularity = domainModel.popularity.toDouble(),
            voteCount = domainModel.vote_count,
            video = domainModel.video,
            voteAverage = domainModel.vote_average.toDouble()
        )
    }

}