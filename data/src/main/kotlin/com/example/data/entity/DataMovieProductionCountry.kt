package com.example.data.entity

import com.example.domain.entity.MovieProductionCountry

data class DataMovieProductionCountry(
    val iso: String,
    val name: String
)

fun DataMovieProductionCountry.toMovieProductionCountry() = MovieProductionCountry(
    iso, name
)

