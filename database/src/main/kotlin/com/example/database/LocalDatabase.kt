package com.example.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.database.dao.MoviesDao
import com.example.database.entity.MovieEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Database(
    entities = [
        MovieEntity::class
    ],
    version = 1
)
@TypeConverters(
    Converters::class
)
abstract class LocalDatabase: RoomDatabase() {
    abstract fun moviesDao(): MoviesDao
}

class Converters {
    @TypeConverter
    fun fromString(value: String): List<Int> {
        return Gson().fromJson(
            value,
            object : TypeToken<List<Int>>() {}.type
        )
    }

    @TypeConverter
    fun fromIntList(value: List<Int>): String {
        return Gson().toJson(value)
    }
}