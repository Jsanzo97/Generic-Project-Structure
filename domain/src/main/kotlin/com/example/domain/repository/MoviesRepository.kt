package com.example.domain.repository

import arrow.core.Either
import com.example.domain.entity.Movie
import com.example.domain.error.MovieError

interface MoviesRepository {

    suspend fun getMovies(): Either<MovieError, Movie>
}