package com.example.movielist.di.local

import androidx.room.Room
import com.example.database.LocalDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

private const val DATABASE_NAME = "localStorage.db"

val localModule = module {

    single {
        Room.databaseBuilder(androidContext(), LocalDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }


    /* DAO */

    single {
        val database = get<LocalDatabase>()
        database.moviesDao()
    }

}