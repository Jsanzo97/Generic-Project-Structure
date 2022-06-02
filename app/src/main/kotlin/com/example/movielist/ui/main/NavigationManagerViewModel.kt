package com.example.movielist.ui.main

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.domain.entity.MovieResult
import com.example.movielist.R
import com.example.movielist.ui.home.HomeFragmentDirections

class NavigationManagerViewModel: ViewModel() {

    fun navigateToDetails(navController: NavController, movieId: Int) {
        val direction = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(
            movieId
        )
        navController.navigate(direction)
    }

}