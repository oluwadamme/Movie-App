package com.example.movieapp.data.datasource

import com.example.movieapp.data.model.Movie
import com.example.movieapp.data.model.People

interface CacheDataSource {

    suspend fun getMoviesFromCache():List<Movie>

    suspend fun getPeopleFromCache():List<People>

    suspend fun saveMovieToCache(movies:List<Movie>)

    suspend fun savePeopleToCache(people:List<People>)
}