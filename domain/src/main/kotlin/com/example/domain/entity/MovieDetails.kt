package com.example.domain.entity

data class MovieDetails(
    val adult: Boolean,
    val backdropPath: String?,
    val belongsToCollection: MovieCollection?,
    val budget: Int,
    val genres: List<MovieGenre>, // ok
    val homepage: String?, // ok
    val id: Int,
    val imdbId: String?,
    val originalLanguage: String, // ok
    val originalTitle: String,
    val overview: String?, // ok
    val popularity: Double,
    val posterPath: String?,
    val productionCompanies: List<MovieProductionCompany>,
    val productionCountries: List<MovieProductionCountry>,
    val releaseDate: String, // ok
    val revenue: Int,
    val runtime: Int?,
    val spokenLanguages: List<MovieSpokenLanguage>,
    val status: String,
    val tagline: String?,
    val title: String, // ok
    val video: Boolean,
    val voteAverage: Double, // ok
    val voteCount: Int
)

