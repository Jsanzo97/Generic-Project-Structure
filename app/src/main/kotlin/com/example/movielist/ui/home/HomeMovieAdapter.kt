package com.example.movielist.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entity.MovieResult
import com.example.movielist.R
import com.google.android.material.textview.MaterialTextView

class HomeMovieAdapter(
    private val movieList: MutableList<MovieResult>
) : RecyclerView.Adapter<HomeMovieAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.home_movie_view, viewGroup,
            false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(item: ViewHolder, position: Int) {
        item.movieTitleLabel.text = movieList[position].title
    }

    fun onNewData(newMovieList: List<MovieResult>) {
        val diffResult = DiffUtil.calculateDiff(HomeMoviesDiffUtilCallback(newMovieList, movieList))
        diffResult.dispatchUpdatesTo(this)

        movieList.clear()
        movieList.addAll(newMovieList)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var movieTitleLabel: MaterialTextView = itemView.findViewById(R.id.movie_title_value_text)

    }
}