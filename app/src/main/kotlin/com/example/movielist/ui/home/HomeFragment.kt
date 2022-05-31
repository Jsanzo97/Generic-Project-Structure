package com.example.movielist.ui.home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.common.fragment.CustomFragment
import com.example.domain.entity.MovieResult
import com.example.movielist.R
import kotlinx.coroutines.flow.collectLatest
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
        movieListRecycler.setHasFixedSize(true)
        movieListRecycler.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )
    }

    private fun setupViewModelStateFlow() {
        lifecycleScope.launchWhenStarted {
            viewModel.homeViewModelSateFlow.collectLatest { state ->
                when (state) {
                    is InitialState -> { /* no-op */ }
                    is RetrievingMoviesFromRemote -> showProgressDialog()
                    is MovieRetrievedFromRemote -> {
                        hideProgressDialog()
                        updateMovies(state.data)
                    }
                    else -> { /* no-op */ }
                }
            }
        }
    }

    private fun updateMovies(movieList: List<MovieResult>) {
        var adapter = movieListRecycler.adapter
        if (adapter != null) {
            adapter = adapter as HomeMovieAdapter
            adapter.onNewData(movieList)
            movieListRecycler.adapter = adapter
        } else {
            movieListRecycler.adapter = HomeMovieAdapter(movieList.toMutableList())
        }
    }

}