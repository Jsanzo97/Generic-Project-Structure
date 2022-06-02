package com.example.data.entity

import com.example.domain.entity.MovieGenre

data class DataMovieGenre(
    val id: Int,
    val name: String
)

fun DataMovieGenre.toMovieGenre() = MovieGenre(
    id, name
)
