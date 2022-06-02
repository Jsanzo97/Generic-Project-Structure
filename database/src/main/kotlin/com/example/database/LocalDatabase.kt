package com.example.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.data.entity.DataMovieCollection
import com.example.database.dao.MoviesDao
import com.example.database.entity.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Database(
    entities = [
        MovieEntity::class,
        MovieDetailsEntity::class
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
    fun fromIntEntityList(value: String): List<Int> {
        return Gson().fromJson(
            value,
            object: TypeToken<List<Int>>() {}.type
        )
    }

    @TypeConverter
    fun toIntEntityList(value: List<Int>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun fromMovieCollectionEntity(value: String?): MovieCollectionEntity? {
        return if (value != null) {
            Gson().fromJson(
                value,
                object: TypeToken<MovieCollectionEntity>() {}.type
            )
        } else {
            null
        }
    }

    @TypeConverter
    fun toMovieCollectionEntity(value: MovieCollectionEntity?): String? {
        return if (value != null) {
            Gson().toJson(value)
        } else {
            null
        }
    }

    @TypeConverter
    fun fromMovieGenreEntityList(value: String): List<MovieGenreEntity> {
        return Gson().fromJson(
            value,
            object: TypeToken<List<MovieGenreEntity>>() {}.type
        )
    }

    @TypeConverter
    fun toMovieGenreEntityList(value: List<MovieGenreEntity>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun fromMovieProductionCompanyEntityList(value: String): List<MovieProductionCompanyEntity> {
        return Gson().fromJson(
            value,
            object: TypeToken<List<MovieProductionCompanyEntity>>() {}.type
        )
    }

    @TypeConverter
    fun toMovieProductionCompanyEntityList(value: List<MovieProductionCompanyEntity>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun fromMovieProductionCountryEntityList(value: String): List<MovieProductionCountryEntity> {
        return Gson().fromJson(
            value,
            object: TypeToken<List<MovieProductionCountryEntity>>() {}.type
        )
    }

    @TypeConverter
    fun toMovieProductionCountryEntityList(value: List<MovieProductionCountryEntity>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toMovieSpokenLanguageEntityList(value: List<MovieSpokenLanguageEntity>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun fromMovieSpokenLanguageEntityList(value: String): List<MovieSpokenLanguageEntity> {
        return Gson().fromJson(
            value,
            object: TypeToken<List<MovieSpokenLanguageEntity>>() {}.type
        )
    }




}