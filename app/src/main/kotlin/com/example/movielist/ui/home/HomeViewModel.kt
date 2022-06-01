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
    private var lastPageRetrieved = 0
    private var lastElementRetrieved = 0

    private val moviesRetrieved = mutableListOf<MovieResult>()
    private val lastVisible = MutableStateFlow(0)

    init {
        viewModelScope.launch {
            lastVisible.collect { checkNeedNewPage() }
        }
    }

    fun getMovies(page: Int = lastPageRetrieved) {
        viewModelScope.launch {
            if (page <= 1) {
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
        if (lastVisible.value + threshold >= lastElementRetrieved) {
            lastElementRetrieved += pageSize
            lastPageRetrieved ++
            getMovies()
        }
    }

    fun notifyLastElementVisible(lastElement: Int) {
        lastVisible.value = lastElement
    }
}