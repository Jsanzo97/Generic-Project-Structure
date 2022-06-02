package com.example.movielist.ui.home

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.common.extensions.lazyBindView
import com.example.common.fragment.CustomFragment
import com.example.domain.entity.MovieResult
import com.example.movielist.R
import com.example.movielist.ui.main.MainActivity
import com.example.movielist.ui.main.NavigationManagerViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment: CustomFragment(R.layout.home_fragment) {

    private val viewModel: HomeViewModel by viewModel()
    private val navigationViewModel: NavigationManagerViewModel by viewModel()

    private val movieListRecycler: RecyclerView by lazyBindView(R.id.home_movies_recycler)
    private val moviesSearchView: SearchView by lazyBindView(R.id.movies_search_view)

    private val onScrollListener = object: RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            viewModel.notifyLastElementVisible(layoutManager.findLastCompletelyVisibleItemPosition())
        }
    }

    private val homeAdapterListener = object: HomeMoviesAdapterListener {
        override fun onItemClick(element: MovieResult) {
            viewModel.saveMovie(element)
        }
    }

    private val moviesSearchViewListener = object: SearchView.OnQueryTextListener {
        override fun onQueryTextChange(newText: String): Boolean {
            return queryMovies(newText)
        }

        override fun onQueryTextSubmit(query: String): Boolean {
            return queryMovies(query)
        }

        fun queryMovies (textToFilter: String): Boolean {
            val adapter = (movieListRecycler.adapter as HomeMovieAdapter)
            adapter.filter.filter(textToFilter)
            return false
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupSearchView()
        setupViewModelStateFlow()

        viewModel.getMovies()
    }

    private fun setupRecyclerView() {
        movieListRecycler.adapter = HomeMovieAdapter(homeAdapterListener)
        movieListRecycler.addOnScrollListener(onScrollListener)
    }

    private fun setupSearchView() {
        moviesSearchView.setOnQueryTextListener(moviesSearchViewListener)
    }

    private fun setupViewModelStateFlow() {
        lifecycleScope.launchWhenStarted {
            viewModel.homeViewModelSateFlow.collect { state ->
                when (state) {
                    is InitialState -> { /* no-op */ }
                    is RetrievingMovies, SavingMovie -> showProgressDialog()
                    is MoviesRetrieved -> {
                        updateMovies(state.movies)
                        hideProgressDialog()
                    }
                    is SavedMovie -> {
                        hideProgressDialog()
                        navigationViewModel.navigateToDetails(
                            findNavController(),
                            state.movieId
                        )
                    }
                    is ErrorInOperation -> showError(state.message)
                }
            }
        }
    }

    override fun onStop() {
        super.onStop()
        viewModel.changeStateTo(InitialState)
    }

    private fun updateMovies(movieList: List<MovieResult>) {
        val adapter = (movieListRecycler.adapter as HomeMovieAdapter)
        adapter.onNewData(movieList)
    }

}