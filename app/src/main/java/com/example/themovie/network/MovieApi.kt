package com.example.themovie.network

import com.example.themovie.network.response.CreditsResponse
import com.example.themovie.network.response.RecommendationsResponse
import com.example.themovie.network.response.TopRatedResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    //https://api.themoviedb.org/3/movie/top_rated
    @GET("movie/top_rated")
    suspend fun topRated(
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ) : TopRatedResponse

    //https://api.themoviedb.org/3/movie/{movie_id}/credits
    @GET("movie/{movie_id}/credits")
    suspend fun getCredits(
        @Path("movie_id") movieId: Int,
        @Query("api_key") api_key: String,
        @Query("language") language: String
    ) : CreditsResponse


    //https://api.themoviedb.org/3/movie/{movie_id}/recommendations
    @GET("movie/{movie_id}/recommendations")
    suspend fun getRecommendations(
        @Path("movie_id") movieId: Int,
        @Query("api_key") api_key: String,
        @Query("language") language: String
    ) : RecommendationsResponse


}
