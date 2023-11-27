package com.example.themovie.network.response

import com.example.themovie.network.model.CastDto
import com.example.themovie.network.model.CrewDto


data class CreditsResponse(
    val cast: List<CastDto>,
    val crew: List<CrewDto>,
    val id: Int
)