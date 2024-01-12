package com.example.movieapp.data.datasource

import com.example.movieapp.data.model.Movie

interface MovieCacheDataSource {

    suspend fun getMoviesFromCache():List<Movie>

    suspend fun saveMovieToCache(movies:List<Movie>)
}