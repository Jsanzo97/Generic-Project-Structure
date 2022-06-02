package com.example.remote.dto.response

import com.example.data.entity.*
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetMoviesDetailsResponse(
    val adult: Boolean,
    @Json(name = "backdrop_path")
    val backdropPath: String?,
    @Json(name = "belongs_to_collection")
    val belongsToCollection: GetMoviesDetailCollection?,
    val budget: Int,
    val genres: List<GetMoviesGenre>,
    val homepage: String?,
    val id: Int,
    @Json(name = "imdb_id")
    val imdbId: String?,
    @Json(name = "original_language")
    val originalLanguage: String,
    @Json(name = "original_title")
    val originalTitle: String,
    val overview: String?,
    val popularity: Double,
    @Json(name = "poster_path")
    val posterPath: String?,
    @Json(name = "production_companies")
    val productionCompanies: List<GetMoviesProductionCompany>,
    @Json(name = "production_countries")
    val productionCountries: List<GetMoviesProductionCountry>,
    @Json(name = "release_date")
    val releaseDate: String,
    val revenue: Int,
    val runtime: Int?,
    @Json(name = "spoken_languages")
    val spokenLanguages: List<GetMoviesSpokenLanguage>,
    val status: String,
    val tagline: String?,
    val title: String,
    val video: Boolean,
    @Json(name = "vote_average")
    val voteAverage: Double,
    @Json(name = "vote_count")
    val voteCount: Int
)

@JsonClass(generateAdapter = true)
data class GetMoviesDetailCollection(
    val id: Int,
    val name: String,
    @Json(name = "poster_path")
    val posterPath: String,
    @Json(name = "backdrop_path")
    val backdropPath: String
)

@JsonClass(generateAdapter = true)
data class GetMoviesGenre(
    val id: Int,
    val name: String
)

@JsonClass(generateAdapter = true)
data class GetMoviesProductionCompany(
    val name: String,
    val id: Int,
    @Json(name = "logo_path")
    val logoPath: String?,
    @Json(name = "origin_country")
    val originCountry: String
)

@JsonClass(generateAdapter = true)
data class GetMoviesProductionCountry(
    @Json(name = "iso_3166_1")
    val iso: String,
    val name: String
)

@JsonClass(generateAdapter = true)
data class GetMoviesSpokenLanguage(
    @Json(name = "iso_639_1")
    val iso: String,
    val name: String
)

fun GetMoviesDetailsResponse.toDataMovieDetails() = DataMovieDetails(
    adult,
    backdropPath,
    belongsToCollection?.toDataMovieCollection(),
    budget,
    genres.map { it.toDataMovieGenre() },
    homepage,
    id,
    imdbId,
    originalLanguage,
    originalTitle,
    overview,
    popularity,
    posterPath,
    productionCompanies.map { it.toDataMovieProductionCompany() },
    productionCountries.map { it.toDataMovieProductionCountry() },
    releaseDate,
    revenue,
    runtime,
    spokenLanguages.map { it.toDataMovieSpokenLanguage() },
    status,
    tagline,
    title,
    video,
    voteAverage,
    voteCount
)

fun GetMoviesDetailCollection.toDataMovieCollection() = DataMovieCollection(
    id, name, posterPath, backdropPath
)

fun GetMoviesGenre.toDataMovieGenre() = DataMovieGenre(
    id, name
)

fun GetMoviesProductionCompany.toDataMovieProductionCompany() = DataMovieProductionCompany(
    name, id, logoPath, originCountry
)

fun GetMoviesProductionCountry.toDataMovieProductionCountry() = DataMovieProductionCountry(
    iso, name
)

fun GetMoviesSpokenLanguage.toDataMovieSpokenLanguage() = DataMovieSpokenLanguage(
    iso, name
)


