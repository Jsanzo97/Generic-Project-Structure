package com.example.movielist.ui.home

import com.example.domain.entity.MovieResult

interface HomeMoviesAdapterListener {

    fun onItemClick(element: MovieResult)

}