package com.example.movielist.ui.details

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.common.EMPTY_STRING
import com.example.common.extensions.lazyBindView
import com.example.common.extensions.toFormattedString
import com.example.common.fragment.CustomFragment
import com.example.common.view.MediaView
import com.example.domain.entity.MovieDetails
import com.example.domain.entity.MovieGenre
import com.example.domain.entity.MovieSpokenLanguage
import com.example.movielist.R
import com.google.android.material.textview.MaterialTextView
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class DetailsFragment: CustomFragment(R.layout.details_fragment) {

    private val viewModel: DetailsViewModel by viewModel()
    private val args: DetailsFragmentArgs by navArgs()

    private val movieDetailsImage: MediaView by lazyBindView(R.id.movie_details_image)
    private val movieDetailsTitle: MaterialTextView by lazyBindView(R.id.movie_details_title_value_text)
    private val movieDetailsPunctuation: MaterialTextView by lazyBindView(R.id.movie_details_punctuation_value_text)
    private val movieDetailsReleaseDate: MaterialTextView by lazyBindView(R.id.movie_details_release_date_value_text)
    private val movieDetailsLanguage: MaterialTextView by lazyBindView(R.id.movie_details_language_value_text)
    private val movieDetailsGenres: MaterialTextView by lazyBindView(R.id.movie_details_genres_value_text)
    private val movieDetailsWebpage: MaterialTextView by lazyBindView(R.id.movie_details_webpage_value_text)
    private val movieDetailsOverview: MaterialTextView by lazyBindView(R.id.movie_details_overview_value_text)



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModelStateFlow()

        viewModel.getDetails(args.movieId)
    }

    private fun setupViewModelStateFlow() {
        lifecycleScope.launchWhenStarted {
            viewModel.detailsViewModelSateFlow.collect { state ->
                when (state) {
                    is InitialState -> { /* no-op */ }
                    is RetrievingDetails -> showProgressDialog()
                    is DetailsRetrieved -> {
                        updateDetails(state.movieDetails)
                        hideProgressDialog()
                    }
                    is ErrorInOperation -> showError(state.message)
                }
            }
        }
    }

    private fun updateDetails(movieDetails: MovieDetails) {
        movieDetailsImage.loadImage(movieDetails.posterPath)
        movieDetailsTitle.text = movieDetails.title
        movieDetailsPunctuation.text = movieDetails.voteAverage.toString()
        movieDetailsReleaseDate.text = movieDetails.releaseDate
        movieDetailsLanguage.text = formatLanguages(movieDetails.spokenLanguages)
        movieDetailsGenres.text = formatGenres(movieDetails.genres)
        movieDetailsWebpage.text = movieDetails.homepage
        movieDetailsOverview.text = movieDetails.overview
    }

    private fun formatLanguages(languages: List<MovieSpokenLanguage>): String {
        return languages.map { language ->
            language.name
        }.toFormattedString()

    }

    private fun formatGenres(genres: List<MovieGenre>): String {
        return genres.map { genre ->
            genre.name
        }.toFormattedString()
    }

}