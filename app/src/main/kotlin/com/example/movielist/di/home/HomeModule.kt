package com.example.movielist.di.home

import com.example.domain.usecase.GetMoviesUseCase
import com.example.domain.usecase.SaveMovieUseCase
import com.example.movielist.ui.home.HomeViewModel
import com.example.movielist.ui.main.NavigationManagerViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {

    viewModel { HomeViewModel(get(), get()) }
    viewModel { NavigationManagerViewModel() }

    factory { GetMoviesUseCase(get()) }
    factory { SaveMovieUseCase(get()) }
}