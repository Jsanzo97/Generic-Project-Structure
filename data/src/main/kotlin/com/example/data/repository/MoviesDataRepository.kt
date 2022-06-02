package com.example.data.repository

import arrow.core.*
import com.example.data.datastore.LocalMoviesDatastore
import com.example.data.datastore.RemoteMoviesDatastore
import com.example.data.entity.toDataMovieResult
import com.example.data.entity.toMovie
import com.example.data.entity.toMovieDetails
import com.example.data.entity.toMovieResult
import com.example.data.error.toMovieError
import com.example.domain.entity.Movie
import com.example.domain.entity.MovieDetails
import com.example.domain.entity.MovieResult
import com.example.domain.error.MovieError
import com.example.domain.repository.MoviesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class MoviesDataRepository(
    private val remoteMoviesDatastore: RemoteMoviesDatastore,
    private val localMoviesDatastore: LocalMoviesDatastore,
    private val dispatcher: CoroutineDispatcher
): MoviesRepository {

    override suspend fun getMovies(page: Int): Either<MovieError, Flow<Movie>> = withContext(dispatcher) {
        remoteMoviesDatastore.getMovies(page).fold(
            ifLeft = {
                localMoviesDatastore.getMovies().fold(
                    ifLeft = { error ->
                        error.toMovieError().left()
                    },
                    ifRight = { dataMovieResult ->
                        dataMovieResult.map { dataMovie ->
                            dataMovie.toMovie()
                        }.right()
                    }
                )
            },
            ifRight = { dataMovie ->
                flowOf(dataMovie.toMovie()).right()
            }
        )
    }

    override suspend fun getMovieDetails(movieId: Int): Either<MovieError, MovieDetails> = withContext(dispatcher) {
        remoteMoviesDatastore.getMovieDetails(movieId).fold(
            ifLeft = { error ->
                error.toMovieError().left()
            },
            ifRight = {
                it.toMovieDetails().right()
            }
        )
    }

    override suspend fun saveMovies(movies: List<MovieResult>) = withContext(dispatcher) {
        localMoviesDatastore.saveMovie(
            movies.map { movieResult ->
                movieResult.toDataMovieResult()
            }
        ).fold(
            ifSome = { error ->
                error.toMovieError().some()
            },
            ifEmpty = {
                None
            }
        )
    }
}