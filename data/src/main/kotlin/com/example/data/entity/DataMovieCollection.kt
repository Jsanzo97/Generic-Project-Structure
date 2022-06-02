package com.example.data.entity

import com.example.domain.entity.MovieCollection

data class DataMovieCollection(
    val id: Int,
    val name: String,
    val posterPath: String,
    val backdropPath: String
)

fun DataMovieCollection.toMovieCollection() = MovieCollection(
    id, name, posterPath, backdropPath
)
