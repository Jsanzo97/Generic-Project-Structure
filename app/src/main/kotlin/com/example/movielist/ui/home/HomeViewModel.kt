package com.example.movielist.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.GetMoviesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getMoviesUseCase: GetMoviesUseCase
): ViewModel() {

    private val _homeViewModelStateFlow = MutableStateFlow<HomeViewState>(InitialState)
    val homeViewModelSateFlow: StateFlow<HomeViewState> get() = _homeViewModelStateFlow

    fun getMovies() {
        viewModelScope.launch {
            _homeViewModelStateFlow.value = RetrievingMoviesFromRemote
            getMoviesUseCase().fold(
                ifLeft = { error ->
                    _homeViewModelStateFlow.value = ErrorOnOperation(error.toString())
                },
                ifRight = { flow ->
                    flow.collectLatest { movieResultList ->
                        _homeViewModelStateFlow.value = MovieRetrievedFromRemote(movieResultList)
                    }
                }
            )
        }
    }
}