package com.example.movielist.ui.details

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.GetMovieDetailsUseCase
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase
): ViewModel() {

    fun getDetails(movieId: Int) {
        viewModelScope.launch {
            getMovieDetailsUseCase(movieId).fold(
                ifLeft = { error ->
                    Log.d("Error", "$error")
                },
                ifRight = { movieDetails ->
                    Log.d("Success", "$movieDetails")
                }
            )
        }
    }

}