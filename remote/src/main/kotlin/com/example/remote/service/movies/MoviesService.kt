package com.example.remote.service.movies

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.example.data.datastore.RemoteMoviesDatastore
import com.example.data.entity.DataMovie
import com.example.data.error.RemoteDataError
import com.example.remote.dto.response.toDataMovie
import com.example.remote.dto.response.toDataMovieDetails
import com.example.remote.service.executeNetworkRequest

class MoviesService(
    private val moviesRemoteWebService: MoviesRemoteWebService,
    private val apiKey: String
): RemoteMoviesDatastore {

    override suspend fun getMovies(page: Int): Either<RemoteDataError, DataMovie> =
        executeNetworkRequest {
            moviesRemoteWebService.getMovies(page, apiKey)
        }.fold(
            ifLeft = { error ->
                error.left()
            },
            ifRight = { response ->
                response.toDataMovie().right()
            }
        )

    override suspend fun getMovieDetails(movieId: Int) =
        executeNetworkRequest {
            moviesRemoteWebService.getMovieDetails(movieId, apiKey)
        }.fold(
            ifLeft = { error ->
                error.left()
            },
            ifRight = { response ->
                response.toDataMovieDetails().right()
            }
        )
}