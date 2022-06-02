package com.example.movielist.ui.home

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.common.extensions.lazyBindView
import com.example.common.fragment.CustomFragment
import com.example.domain.entity.MovieResult
import com.example.movielist.R
import com.example.movielist.ui.main.NavigationManagerViewModel
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment: CustomFragment(R.layout.home_fragment) {

    private val viewModel: HomeViewModel by viewModel()
    private val navigationViewModel: NavigationManagerViewModel by viewModel()

    private val movieListRecycler: RecyclerView by lazyBindView(R.id.home_movies_recycler)

    private val homeAdapterListener = object: HomeMoviesAdapterListener {
        override fun onItemClick(element: MovieResult) {
            navigationViewModel.navigateToDetails(
                findNavController(),
                element
            )
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupViewModelStateFlow()
        viewModel.getMovies()
    }

    private fun setupRecyclerView() {
        movieListRecycler.adapter = HomeMovieAdapter(homeAdapterListener)
        movieListRecycler.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                viewModel.notifyLastElementVisible(layoutManager.findLastCompletelyVisibleItemPosition())
            }
        })
    }

    private fun setupViewModelStateFlow() {
        lifecycleScope.launchWhenStarted {
            viewModel.homeViewModelSateFlow.collect { state ->
                when (state) {
                    is InitialState -> { /* no-op */ }
                    is RetrievingMovies -> showProgressDialog()
                    is MoviesRetrieved -> {
                        updateMovies(state.movies)
                        hideProgressDialog()
                    }
                    is ErrorOnOperation -> showError(state.message)
                }
            }
        }
    }

    private fun updateMovies(movieList: List<MovieResult>) {
        val adapter = (movieListRecycler.adapter as HomeMovieAdapter)
        adapter.submitList(movieList)
    }

}