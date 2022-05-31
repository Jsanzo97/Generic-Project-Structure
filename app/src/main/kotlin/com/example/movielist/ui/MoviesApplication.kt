package com.example.movielist.ui

import android.app.Application
import com.example.movielist.di.data.dataModule
import com.example.movielist.di.home.homeModule
import com.example.movielist.di.remote.remoteModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MoviesApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MoviesApplication)

            modules(
                listOf(
                    remoteModule,
                    dataModule,
                    homeModule
                )
            )
        }
    }

}