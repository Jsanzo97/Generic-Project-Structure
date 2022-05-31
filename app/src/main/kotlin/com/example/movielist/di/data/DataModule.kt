package com.example.movielist.di.data

import com.example.data.datastore.RemoteMoviesDatastore
import com.example.data.repository.MoviesDataRepository
import com.example.domain.repository.MoviesRepository
import com.example.movielist.BuildConfig
import com.example.remote.service.movies.MoviesService
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.create

val dataModule = module {

    /* MOVIES SERVICE */

    single<MoviesRepository> { MoviesDataRepository(get(), Dispatchers.IO) }

    single<RemoteMoviesDatastore> {
        MoviesService(get<Retrofit>().create(), BuildConfig.SERVER_API_KEY)
    }
}
