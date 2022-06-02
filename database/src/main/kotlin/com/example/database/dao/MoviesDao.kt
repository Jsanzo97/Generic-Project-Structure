package com.example.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.database.entity.MovieDetailsEntity
import com.example.database.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun saveMovie(movieEntity: MovieEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun saveMovieDetails(movieDetailsEntity: MovieDetailsEntity)

    @Query("select * from Movies")
    abstract fun getMovies(): List<MovieEntity>

    @Query("select * from `Movie details` where id == :movieId")
    abstract fun getMovieDetails(movieId: Int): MovieDetailsEntity

}