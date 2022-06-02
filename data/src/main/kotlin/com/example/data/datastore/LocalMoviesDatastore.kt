package com.example.data.datastore

import arrow.core.Either
import arrow.core.Option
import com.example.data.entity.DataMovie
import com.example.data.entity.DataMovieDetails
import com.example.data.entity.DataMovieResult
import com.example.data.error.LocalDataError
import kotlinx.coroutines.flow.Flow

interface LocalMoviesDatastore {

    suspend fun saveMovie(dataMovie: DataMovieResult): Option<LocalDataError>
    suspend fun saveMovieDetails(dataMovieDetails: DataMovieDetails): Option<LocalDataError>
    suspend fun getMovies(): Either<LocalDataError, DataMovie>
    suspend fun getMovieDetails(movieId: Int): Either<LocalDataError, DataMovieDetails>
}