package com.example.movielist.ui.home

import com.example.domain.entity.Movie

sealed class HomeViewState

object InitialState: HomeViewState()
object RetrievingMoviesFromRemote: HomeViewState()
class MoviesRetrievedFromRemote(val data: Movie): HomeViewState()
class ErrorOnOperation(val message: String): HomeViewState()