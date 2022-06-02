package com.example.data.entity

import com.example.domain.entity.MovieProductionCompany

data class DataMovieProductionCompany(
    val name: String,
    val id: Int,
    val logoPath: String?,
    val originCountry: String
)

fun DataMovieProductionCompany.toMovieProductionCompany() = MovieProductionCompany(
    name, id, logoPath, originCountry
)

