package com.example.movielist.ui.home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.common.fragment.CustomFragment
import com.example.domain.entity.MovieResult
import com.example.movielist.R
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment: CustomFragment(R.layout.home_fragment) {

    private val viewModel: HomeViewModel by viewModel()

    private val movieListRecycler by lazy { requireView().findViewById<RecyclerView>(R.id.home_movies_recycler) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupViewModelStateFlow()
        viewModel.getMovies()
    }

    private fun setupRecyclerView() {
        movieListRecycler.adapter = HomeMovieAdapter()
        movieListRecycler.setHasFixedSize(true)
        movieListRecycler.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )
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
                    else -> { /* no-op */ }
                }
            }
        }
    }

    private fun updateMovies(movieList: List<MovieResult>) {
        val adapter = (movieListRecycler.adapter as HomeMovieAdapter)
        adapter.submitList(movieList)
    }

}