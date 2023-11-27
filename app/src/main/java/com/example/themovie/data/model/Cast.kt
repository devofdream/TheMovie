package com.example.themovie.data.model

import com.example.themovie.domain.model.Cast
import com.example.themovie.network.model.CastDto

// CastDto >> Cast
fun CastDto.asDomain() = Cast(
    id = id,
    adult = adult,
    castId = castId,
    character = character,
    creditId = creditId,
    gender = gender,
    knownForDepartment = knownForDepartment,
    name = name,
    order = order,
    originalName = originalName,
    popularity = popularity,
    profilePath = profilePath
)



