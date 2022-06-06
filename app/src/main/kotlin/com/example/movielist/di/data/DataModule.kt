package com.example.movielist.di.data

import com.example.data.datastore.LocalMoviesDatastore
import com.example.data.datastore.RemoteMoviesDatastore
import com.example.data.repository.MoviesDataRepository
import com.example.database.storage.MoviesStorage
import com.example.domain.repository.MoviesRepository
import com.example.movielist.BuildConfig
import com.example.movielist.di.remote.APP_OK_HTTP_CLIENT
import com.example.movielist.di.remote.APP_WS
import com.example.remote.service.movies.MoviesService
import kotlinx.coroutines.Dispatchers
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.create

val dataModule = module {

    /* MOVIES SERVICE */

    single<MoviesRepository> { MoviesDataRepository(get(), get(), Dispatchers.IO) }

    single<RemoteMoviesDatastore> {
        MoviesService(get<Retrofit>(named(APP_WS)).create(), BuildConfig.SERVER_API_KEY)
    }

    single<LocalMoviesDatastore> { MoviesStorage(get()) }

}
