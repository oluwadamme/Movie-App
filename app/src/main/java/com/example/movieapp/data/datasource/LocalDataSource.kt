package com.example.movieapp.data.datasource

import com.example.movieapp.data.model.Movie
import com.example.movieapp.data.model.People

interface LocalDataSource {
    suspend fun getMoviesFromDB():List<Movie>

    suspend fun saveMovieIntoDB(movies:List<Movie>)

    suspend fun getPeopleFromDB():List<People>

    suspend fun savePeopleIntoDB(people:List<People>)

    suspend fun clearAll()
}