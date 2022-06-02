package com.example.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.database.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun saveMovie(movieEntity: MovieEntity)



    @Query("select * from Movies")
    abstract fun getMovies(): Flow<List<MovieEntity>>


}