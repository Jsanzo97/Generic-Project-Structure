package com.example.movielist

import com.example.movielist.ui.details.DetailsViewModelTest
import com.example.movielist.ui.home.HomeViewModelTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.runner.RunWith
import org.junit.runners.Suite

@ExperimentalCoroutinesApi
@RunWith(Suite::class)
@Suite.SuiteClasses(
    HomeViewModelTest::class,
    DetailsViewModelTest::class
)
class MovieTestSuite