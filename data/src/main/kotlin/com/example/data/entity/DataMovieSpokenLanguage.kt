package com.example.data.entity

import com.example.domain.entity.MovieSpokenLanguage

data class DataMovieSpokenLanguage(
    val iso: String,
    val name: String
)

fun DataMovieSpokenLanguage.toMovieSpokenLanguage() = MovieSpokenLanguage(
    iso, name
)