package com.example.remote.dto.response

import com.example.data.entity.DataMovie
import com.example.data.entity.DataMovieResult
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetMoviesResponse(
    val page: Int,
    val results: List<MoviesResponseResult>,
    @Json(name = "total_results")
    val totalResults: Int,
    @Json(name = "total_pages")
    val totalPages: Int
)

@JsonClass(generateAdapter = true)
data class MoviesResponseResult(
    @Json(name = "poster_path")
    val posterPath: String?,
    val adult: Boolean,
    val overview: String,
    @Json(name = "release_date")
    val releaseDate: String,
    @Json(name = "genre_ids")
    val genreIds: List<Int>,
    val id: Int,
    @Json(name = "original_title")
    val originalTitle: String,
    @Json(name = "original_language")
    val originalLanguage: String,
    val title: String,
    @Json(name = "backdrop_path")
    val backdropPath: String?,
    val popularity: Double,
    @Json(name = "vote_count")
    val voteCount: Int,
    val video: Boolean,
    @Json(name = "vote_average")
    val voteAverage: Double
)

fun GetMoviesResponse.toDataMovie() = DataMovie(
    page,
    results.map { it.toDataMovie() },
    totalResults,
    totalPages
)

fun MoviesResponseResult.toDataMovie() = DataMovieResult(
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