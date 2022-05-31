package com.example.movielist.ui.home

import com.example.domain.entity.MovieResult

sealed class HomeViewState

object InitialState: HomeViewState()
object RetrievingMoviesFromRemote: HomeViewState()
class MovieRetrievedFromRemote(val data: List<MovieResult>): HomeViewState()
class ErrorOnOperation(val message: String): HomeViewState()