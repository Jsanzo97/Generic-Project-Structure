package com.example.domain.usecase

import com.example.domain.entity.MovieResult
import com.example.domain.repository.MoviesRepository

class SaveMovieUseCase(private val movieRepository: MoviesRepository) {

    suspend operator fun invoke(movie: MovieResult) = movieRepository.saveMovie(movie)

}