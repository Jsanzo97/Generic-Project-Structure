package com.example.domain.repository

import arrow.core.Either
import arrow.core.Option
import com.example.domain.entity.Movie
import com.example.domain.entity.MovieResult
import com.example.domain.error.MovieError
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    suspend fun getMovies(page: Int): Either<MovieError, Flow<Movie>>
    suspend fun saveMovies(movies: List<MovieResult>): Option<MovieError>

}