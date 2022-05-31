package com.example.movielist.ui.home

import androidx.recyclerview.widget.DiffUtil
import com.example.domain.entity.MovieResult

class HomeMoviesDiffUtilCallback(
    private val newList: List<MovieResult>,
    private val oldList: List<MovieResult>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return newList[newItemPosition].id == oldList[oldItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return newList[newItemPosition] == oldList[oldItemPosition]
    }

}