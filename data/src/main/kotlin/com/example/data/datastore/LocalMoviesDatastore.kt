package com.example.data.datastore

import arrow.core.Either
import arrow.core.Option
import com.example.data.entity.DataMovieResult
import com.example.data.error.LocalDataError
import kotlinx.coroutines.flow.Flow

interface LocalMoviesDatastore {

    suspend fun saveMovie(dataMovie: List<DataMovieResult>): Option<LocalDataError>
    suspend fun getMovies() : Either<LocalDataError, Flow<List<DataMovieResult>>>
}