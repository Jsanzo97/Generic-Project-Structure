package com.example.database.storage

import arrow.core.*
import com.example.data.datastore.LocalMoviesDatastore
import com.example.data.entity.DataMovie
import com.example.data.entity.DataMovieDetails
import com.example.data.entity.DataMovieResult
import com.example.data.error.LocalDataError
import com.example.data.error.ReadingError
import com.example.data.error.WritingError
import com.example.database.dao.MoviesDao
import com.example.database.entity.toDataMovieDetails
import com.example.database.entity.toDataMovieResult
import com.example.database.entity.toMovieDetailsEntity
import com.example.database.entity.toMovieEntity

class MoviesStorage(
    private val moviesDao: MoviesDao
): LocalMoviesDatastore {

    override suspend fun getMovies(): Either<LocalDataError, DataMovie> {
        return try {
            DataMovie(
                0,
                moviesDao.getMovies().map { it.toDataMovieResult() },
                0,
                0,
            ).right()
        } catch (_: Exception) {
            ReadingError.left()
        }
    }

    override suspend fun getMovieDetails(movieId: Int): Either<LocalDataError, DataMovieDetails> {
        return try {
            moviesDao.getMovieDetails(movieId).toDataMovieDetails().right()
        } catch (e: Exception) {
            ReadingError.left()
        }
    }

    override suspend fun saveMovie(dataMovie: DataMovieResult): Option<LocalDataError> {
        return try {
            moviesDao.saveMovie(dataMovie.toMovieEntity())
            None
        } catch (e: Exception) {
            WritingError.some()
        }
    }

    override suspend fun saveMovieDetails(dataMovieDetails: DataMovieDetails): Option<LocalDataError> {
        return try {
            moviesDao.saveMovieDetails(dataMovieDetails.toMovieDetailsEntity())
            None
        } catch (e: Exception) {
            ReadingError.some()
        }
    }

}