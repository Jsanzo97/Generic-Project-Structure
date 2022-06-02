package com.example.movielist.ui.main

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.domain.entity.MovieResult
import com.example.movielist.R

class NavigationManagerViewModel: ViewModel() {

    fun navigateToDetails(navController: NavController, element: MovieResult) {
        navController.navigate(R.id.action_homeFragment_to_detailsFragment)
    }

    fun navigateToHome(navController: NavController) {
        navController.navigate(R.id.action_detailsFragment_to_homeFragment)
    }

}