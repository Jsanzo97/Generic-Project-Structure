package com.example.movielist.di.home

import com.example.domain.usecase.GetMoviesUseCase
import com.example.movielist.ui.home.HomeViewModel
import com.example.movielist.ui.main.NavigationManagerViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {

    viewModel { HomeViewModel(get()) }
    viewModel { NavigationManagerViewModel() }

    factory { GetMoviesUseCase(get()) }
}