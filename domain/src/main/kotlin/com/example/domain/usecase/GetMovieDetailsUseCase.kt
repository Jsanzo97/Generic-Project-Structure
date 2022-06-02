package com.example.domain.usecase

import com.example.domain.repository.MoviesRepository

class GetMovieDetailsUseCase(private val movieRepository: MoviesRepository) {

    suspend operator fun invoke(movieId: Int) = movieRepository.getMovieDetails(movieId)

}