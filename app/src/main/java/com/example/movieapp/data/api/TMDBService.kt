package com.example.movieapp.data.api

import com.example.movieapp.data.model.MovieList
import com.example.movieapp.data.model.PeopleList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TMDBService {
    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key") apiKey: String): Response<MovieList>

    @GET("person/popular")
    suspend fun getPopularPeople(@Query("api_key") apiKey: String): Response<PeopleList>
}