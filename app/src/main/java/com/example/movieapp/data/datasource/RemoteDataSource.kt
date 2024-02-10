package com.example.movieapp.data.datasource

import com.example.movieapp.data.model.MovieList
import com.example.movieapp.data.model.PeopleList
import retrofit2.Response

interface RemoteDataSource {
    suspend fun getMovies():Response<MovieList>
    suspend fun getPeople(page: Int):Response<PeopleList>

}