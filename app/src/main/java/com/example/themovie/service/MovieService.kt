package com.example.themovie.service

import com.example.themovie.network.response.TopRatedResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    //https://api.themoviedb.org/3/movie/top_rated
    @GET("movie/top_rated")
    suspend fun topRated(
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ) : TopRatedResponse

}
