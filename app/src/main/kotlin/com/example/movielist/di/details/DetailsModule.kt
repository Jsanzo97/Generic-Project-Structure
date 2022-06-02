package com.example.movielist.di.details

import com.example.domain.usecase.GetMovieDetailsUseCase
import com.example.movielist.ui.details.DetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val detailsModule = module {

    viewModel { DetailsViewModel(get()) }

    factory { GetMovieDetailsUseCase(get()) }
}