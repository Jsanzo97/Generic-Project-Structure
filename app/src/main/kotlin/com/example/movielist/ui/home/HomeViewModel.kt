package com.example.movielist.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.MovieResult
import com.example.domain.usecase.GetMoviesUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getMoviesUseCase: GetMoviesUseCase
): ViewModel() {

    private val _homeViewModelStateFlow = MutableStateFlow<HomeViewState>(InitialState)
    val homeViewModelSateFlow: StateFlow<HomeViewState> get() = _homeViewModelStateFlow

    private val threshold = 10
    private val pageSize = 20
    private var nextPageToRetrieve = 1
    private var lastElementRetrieved = 0

    private val moviesRetrieved = mutableListOf<MovieResult>()
    private var lastVisible = 0

    fun getMovies(page: Int = nextPageToRetrieve) {
        viewModelScope.launch {
            if (page == 1) {
                _homeViewModelStateFlow.value = RetrievingMovies
            }

            getMoviesUseCase(page).fold(
                ifLeft = { error ->
                    _homeViewModelStateFlow.value = ErrorOnOperation(error.toString())
                },
                ifRight = { flow ->
                    flow.collect { movies ->
                        movies.results.forEach { movieResult ->
                            if (!moviesRetrieved.contains(movieResult)) {
                                moviesRetrieved.addAll(movies.results)
                            }
                        }
                        _homeViewModelStateFlow.value = MoviesRetrieved(moviesRetrieved)
                    }
                }
            )
        }
    }

    private fun checkNeedNewPage() {
        if (lastVisible + threshold >= lastElementRetrieved) {
            lastElementRetrieved += pageSize
            nextPageToRetrieve ++
            getMovies()
        }
    }

    fun notifyLastElementVisible(lastElement: Int) {
        if (lastVisible != lastElement) {
            lastVisible = lastElement
            checkNeedNewPage()
        }
    }
}