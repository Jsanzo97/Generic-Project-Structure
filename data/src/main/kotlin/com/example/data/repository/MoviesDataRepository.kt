package com.example.data.repository

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.example.data.datastore.RemoteMoviesDatastore
import com.example.data.entity.toMovie
import com.example.data.error.toMovieError
import com.example.domain.entity.Movie
import com.example.domain.error.MovieError
import com.example.domain.repository.MoviesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class MoviesDataRepository(
    private val remoteMoviesDatastore: RemoteMoviesDatastore,
    private val dispatcher: CoroutineDispatcher
): MoviesRepository {

    override suspend fun getMovies(): Either<MovieError, Movie> = withContext(dispatcher) {
        remoteMoviesDatastore.getMovies().fold(
            ifLeft = { error ->
                error.toMovieError().left()
            },
            ifRight = { movie ->
                movie.toMovie().right()
            }
        )
    }

}