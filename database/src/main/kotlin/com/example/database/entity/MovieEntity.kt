package com.example.database.entity

import androidx.room.Entity
import com.example.data.entity.DataMovieResult

@Entity(
    tableName = "Movies",
    primaryKeys = ["id"]
)
data class MovieEntity(
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
    val popularity: Double,
    val voteCount: Int,
    val video: Boolean,
    val voteAverage: Double
)

fun DataMovieResult.toMovieEntity() = MovieEntity(
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

fun MovieEntity.toDataMovieResult() = DataMovieResult(
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