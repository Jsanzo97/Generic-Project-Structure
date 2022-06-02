package com.example.database.storage

import arrow.core.*
import com.example.data.datastore.LocalMoviesDatastore
import com.example.data.entity.DataMovie
import com.example.data.entity.DataMovieResult
import com.example.data.error.LocalDataError
import com.example.data.error.ReadingError
import com.example.data.error.WritingError
import com.example.database.dao.MoviesDao
import com.example.database.entity.toDataMovieResult
import com.example.database.entity.toMovieEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import java.lang.Exception

class MoviesStorage(
    private val moviesDao: MoviesDao
): LocalMoviesDatastore {

    override suspend fun getMovies(): Either<LocalDataError, Flow<DataMovie>> {
        return try {
            moviesDao.getMovies().map { movieEntityList ->
                DataMovie(
                    0,
                    movieEntityList.map { movieEntity ->
                        movieEntity.toDataMovieResult()
                    },
                    0,
                    0,
                )
            }.right()
        } catch (e: Exception) {
            ReadingError.left()
        }
    }

    override suspend fun saveMovie(dataMovie: List<DataMovieResult>): Option<LocalDataError> {
        return try {
            dataMovie.forEach { movie ->
                moviesDao.saveMovie(movie.toMovieEntity())
            }
            None
        } catch (_: Exception) {
            WritingError.some()
        }
    }

}