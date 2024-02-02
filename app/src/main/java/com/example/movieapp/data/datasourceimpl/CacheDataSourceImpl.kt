package com.example.movieapp.data.datasourceimpl

import com.example.movieapp.data.datasource.CacheDataSource
import com.example.movieapp.data.model.Movie
import com.example.movieapp.data.model.People

class CacheDataSourceImpl:CacheDataSource {

    private var movieList=ArrayList<Movie>()
    private var peopleList=ArrayList<People>()
    override suspend fun getMoviesFromCache(): List<Movie> {
        return movieList
    }

    override suspend fun getPeopleFromCache(): List<People> {
        return peopleList
    }

    override suspend fun saveMovieToCache(movies: List<Movie>) {
        movieList.clear()
        movieList=ArrayList(movies)
    }

    override suspend fun savePeopleToCache(people: List<People>) {
       peopleList.clear()
        peopleList= ArrayList(people)
    }
}