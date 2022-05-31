package com.example.movielist.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.movielist.R
import com.example.movielist.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity: AppCompatActivity() {

    private val viewModel: HomeViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.home_fragment)

        viewModel.getMovies()
    }

}