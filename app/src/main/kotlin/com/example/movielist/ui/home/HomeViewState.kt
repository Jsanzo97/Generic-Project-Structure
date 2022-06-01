package com.example.movielist.ui.home

import com.example.domain.entity.MovieResult

sealed class HomeViewState

object InitialState: HomeViewState()
object RetrievingMovies: HomeViewState()
class MoviesRetrieved(val movies: List<MovieResult>): HomeViewState()
class ErrorOnOperation(val message: String): HomeViewState()