package com.example.data.datastore

import arrow.core.Either
import com.example.data.entity.DataMovie
import com.example.data.entity.DataMovieDetails
import com.example.data.error.RemoteDataError

interface RemoteMoviesDatastore {

    suspend fun getMovies(page: Int): Either<RemoteDataError, DataMovie>
    suspend fun getMovieDetails(movieId: Int): Either<RemoteDataError, DataMovieDetails>
}