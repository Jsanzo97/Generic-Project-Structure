package com.example.movielist.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.GetMoviesUseCase
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getMoviesUseCase: GetMoviesUseCase
): ViewModel() {

    fun getMovies() {
        viewModelScope.launch {
            getMoviesUseCase().fold(
                ifLeft = {
                    Log.d("Error", it.toString())
                },
                ifRight = {
                    Log.d("OK", it.toString())
                }
            )
        }
    }
}