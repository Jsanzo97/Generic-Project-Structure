package com.example.data.entity

import com.example.domain.entity.Movie

data class DataMovie(
    val page: Int,
    val results: List<DataMovieResult>,
    val totalResults: Int,
    val totalPages: Int
)

fun DataMovie.toMovie() = Movie(
    page,
    results.map { it.toMovieResult() },
    totalResults,
    totalPages
)