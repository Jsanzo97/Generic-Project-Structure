package com.example.movielist.ui.details

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.common.fragment.CustomFragment
import com.example.movielist.R
import com.example.movielist.ui.main.NavigationManagerViewModel
import com.google.android.material.button.MaterialButton
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsFragment: CustomFragment(R.layout.details_fragment) {

    private val navigationViewModel: NavigationManagerViewModel by viewModel()

    private val backButton by lazy { requireView().findViewById<MaterialButton>(R.id.volver) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        backButton.setOnClickListener {
            navigationViewModel.navigateToHome(findNavController())
        }


    }

}