package com.example.movieapp.data.datasourceimpl

import com.example.movieapp.data.api.TMDBService
import com.example.movieapp.data.datasource.RemoteDataSource
import com.example.movieapp.data.model.MovieList
import com.example.movieapp.data.model.PeopleList
import retrofit2.Response

class RemoteDataSourceImpl(private val tmdbService: TMDBService, private val apiKey:String):RemoteDataSource {
    override suspend fun getMovies(): Response<MovieList> {
       return tmdbService.getPopularMovies(apiKey)
    }

    override suspend fun getPeople(): Response<PeopleList> {
      return  tmdbService.getPopularPeople(apiKey)
    }
}