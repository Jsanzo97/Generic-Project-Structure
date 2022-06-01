package com.example.movielist.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.common.view.MediaView
import com.example.domain.entity.MovieResult
import com.example.movielist.R
import com.google.android.material.textview.MaterialTextView

class HomeMovieAdapter: ListAdapter<MovieResult, HomeMovieAdapter.ViewHolder>(HomeMoviesDiffUtilCallback()) {

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.home_movie_view, viewGroup,
            false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(item: ViewHolder, position: Int) {
        val element = getItem(position)
        item.movieTitleLabel.text = element.title
        item.movieImage.loadImage(element.posterPath)
        item.movieReleaseDate.text = element.releaseDate
        item.moviePopularity.text = element.popularity.toString()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val movieTitleLabel: MaterialTextView = itemView.findViewById(R.id.movie_title_value_text)
        val movieImage: MediaView = itemView.findViewById(R.id.movie_image)
        val movieReleaseDate: MaterialTextView = itemView.findViewById(R.id.movie_release_date_text)
        val moviePopularity: MaterialTextView = itemView.findViewById(R.id.movie_popularity_text)
    }
}