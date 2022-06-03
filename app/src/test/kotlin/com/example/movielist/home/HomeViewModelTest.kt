package com.example.movielist.home

import com.example.domain.usecase.GetMoviesUseCase
import com.example.domain.usecase.SaveMovieUseCase
import com.example.movielist.ui.home.HomeViewModel
import com.example.movielist.ui.home.HomeViewState
import kotlinx.coroutines.flow.StateFlow
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Spy
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class HomeViewModelTest {

    private lateinit var homeViewModel: HomeViewModel

    @Mock
    private val mockedGetMoviesUseCase = Mockito.mock(GetMoviesUseCase::class.java)

    @Mock
    private val mockedSaveMovieUseCase = Mockito.mock(SaveMovieUseCase::class.java)

    @Spy
    private lateinit var homeViewModelStateFlow: StateFlow<HomeViewState>


}