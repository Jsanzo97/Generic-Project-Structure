package com.example.data.entity

import com.example.domain.entity.MovieResult

data class DataMovieResult(
    val posterPath: String?,
    val adult: Boolean,
    val overview: String,
    val releaseDate: String,
    val genreIds: List<Int>,
    val id: Int,
    val originalTitle: String,
    val originalLanguage: String,
    val title: String,
    val backdropPath: String?,
    val popularity: Number,
    val voteCount: Int,
    val video: Boolean,
    val voteAverage: Number
)

fun DataMovieResult.toMovieResult() = MovieResult(
    posterPath,
    adult,
    overview,
    releaseDate,
    genreIds,
    id,
    originalTitle,
    originalLanguage,
    title,
    backdropPath,
    popularity,
    voteCount,
    video,
    voteAverage
)