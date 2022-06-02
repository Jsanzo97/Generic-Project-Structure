package com.example.movielist.ui.details

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.example.common.fragment.CustomFragment
import com.example.movielist.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsFragment: CustomFragment(R.layout.details_fragment) {

    private val viewModel: DetailsViewModel by viewModel()
    private val args: DetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getDetails(args.movieId)
    }

}