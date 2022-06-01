package com.example.remote.service.movies

import com.example.remote.dto.response.GetMoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesRemoteWebService {

    @GET("popular")
    suspend fun getMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Response<GetMoviesResponse>

}