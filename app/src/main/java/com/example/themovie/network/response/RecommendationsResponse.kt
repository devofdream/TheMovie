package com.example.themovie.network.response


import com.example.themovie.network.model.ResultDto
import com.google.gson.annotations.SerializedName

data class RecommendationsResponse(
    val page: Int,
    val results: List<ResultDto>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)