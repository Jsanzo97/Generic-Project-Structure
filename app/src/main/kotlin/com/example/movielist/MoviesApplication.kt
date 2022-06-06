package com.example.movielist

import android.app.Application
import com.example.movielist.di.data.dataModule
import com.example.movielist.di.details.detailsModule
import com.example.movielist.di.home.homeModule
import com.example.movielist.di.local.localModule
import com.example.movielist.di.remote.appRemoteModule
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
                    appRemoteModule,
                    localModule,
                    dataModule,
                    homeModule,
                    detailsModule
                )
            )
        }
    }

}