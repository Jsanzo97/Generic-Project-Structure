package com.example.movielist.ui.details

import com.example.domain.entity.MovieDetails

sealed class DetailsViewState

object InitialState: DetailsViewState()
object RetrievingDetails: DetailsViewState()
class DetailsRetrieved(val movieDetails: MovieDetails): DetailsViewState()
class ErrorInOperation(val message: String): DetailsViewState()