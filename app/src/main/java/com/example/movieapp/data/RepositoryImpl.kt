package com.example.movieapp.data

import com.example.movieapp.data.datasource.CacheDataSource
import com.example.movieapp.data.datasource.LocalDataSource
import com.example.movieapp.data.datasource.RemoteDataSource
import com.example.movieapp.data.model.Movie
import com.example.movieapp.data.model.People
import com.example.movieapp.domain.repository.Repository

class RepositoryImpl(
    val remoteDataSource: RemoteDataSource,
    val localDataSource: LocalDataSource,
    val cacheDataSource: CacheDataSource
) : Repository {
    override suspend fun getMovies(): List<Movie>? {
        return getMoviesFromCache()
    }
var pageNumber:Int=1

    override suspend fun updateMovies(): List<Movie> {
        val listOfNewMovies = getMoviesFromApi()
        localDataSource.clearAll()
        localDataSource.saveMovieIntoDB(listOfNewMovies)
        cacheDataSource.saveMovieToCache(listOfNewMovies)
        return listOfNewMovies
    }

    override suspend fun getPeople(page:Int): List<People>? {
        pageNumber =page
        return getPeopleFromCache()
    }


    suspend fun getMoviesFromApi(): List<Movie> {
        lateinit var movieList: List<Movie>

        try {
            val response = remoteDataSource.getMovies()
            val body = response.body()
            if (body != null) {
                movieList = body.movies
            }
        } catch (_: java.lang.Exception) {

        }
        return movieList
    }

    suspend fun getMoviesFromRoom(): List<Movie> {
        lateinit var movieList: List<Movie>

        try {
            val response = localDataSource.getMoviesFromDB()
            movieList = response
        } catch (_: Exception) {

        }
        if (movieList.size > 0) {
            return movieList
        } else {
            movieList = getMoviesFromApi()
            localDataSource.saveMovieIntoDB(movieList)
        }
        return movieList
    }


    private suspend fun getMoviesFromCache(): List<Movie>? {
        lateinit var movieList: List<Movie>

        try {
            val response = cacheDataSource.getMoviesFromCache()
            movieList = response
        } catch (_: Exception) {

        }
        if (movieList.size > 0) {
            return movieList
        } else {
            movieList = getMoviesFromRoom()
            cacheDataSource.saveMovieToCache(movieList)
        }
        return movieList
    }

    private suspend fun getPeopleFromCache(): List<People>? {
        lateinit var peopleList: List<People>

        try {
            val response = cacheDataSource.getPeopleFromCache()
            peopleList = response
        } catch (_: Exception) {

        }
        if (peopleList.size > 0) {
            return peopleList
        } else {
            peopleList = getPeopleFromRoom()
            cacheDataSource.savePeopleToCache(peopleList)
        }
        return peopleList
    }

    suspend fun getPeopleFromRoom(): List<People> {
        lateinit var peopleList: List<People>

        try {
            val response = localDataSource.getPeopleFromDB()
            peopleList = response
        } catch (_: Exception) {

        }
        if (peopleList.size > 0) {
            return peopleList
        } else {
            peopleList = getPeopleFromApi(pageNumber)
            localDataSource.savePeopleIntoDB(peopleList)
        }
        return peopleList
    }

    suspend fun getPeopleFromApi(page:Int): List<People> {
        lateinit var peopleList: List<People>

        try {
            val response = remoteDataSource.getPeople(page)
            val body = response.body()
            if (body != null) {
                peopleList = body.movies
            }
        } catch (_: java.lang.Exception) {

        }
        return peopleList
    }
}