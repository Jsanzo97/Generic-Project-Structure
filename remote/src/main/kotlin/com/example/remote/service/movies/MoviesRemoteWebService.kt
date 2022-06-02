package com.example.remote.service.movies

import com.example.remote.dto.response.GetMoviesDetailsResponse
import com.example.remote.dto.response.GetMoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesRemoteWebService {

    @GET("popular")
    suspend fun getMovies(
        @Query("page") page: Int,
        @Query("api_key") apiKey: String
    ): Response<GetMoviesResponse>

    @GET("{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): Response<GetMoviesDetailsResponse>

}