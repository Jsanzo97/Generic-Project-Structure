package com.example.movielist.di.home

import com.example.domain.usecase.GetMoviesUseCase
import com.example.movielist.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {

    viewModel { HomeViewModel(get()) }

    factory { GetMoviesUseCase(get()) }
}