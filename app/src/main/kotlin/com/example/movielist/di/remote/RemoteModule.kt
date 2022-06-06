package com.example.movielist.di.remote

import com.example.movielist.BuildConfig
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

private const val TIMEOUT = 15L

val remoteModule = module {

    single {
        Retrofit.Builder()
            .client(get())
            .baseUrl(BuildConfig.SERVER_ENDPOINT)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    single {
        OkHttpClient.Builder()
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
            .build()
    }
}