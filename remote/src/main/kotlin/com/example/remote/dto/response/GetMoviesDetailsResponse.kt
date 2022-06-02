package com.example.remote.dto.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetMoviesDetailsResponse(
    val adult: Boolean,
    @Json(name = "backdrop_path")
    val backdropPath: String,



)
