package com.example.domain.usecase

import com.example.domain.repository.MoviesRepository

class GetMoviesUseCase(private val movieRepository: MoviesRepository) {

    suspend operator fun invoke() = movieRepository.getMovies()

}