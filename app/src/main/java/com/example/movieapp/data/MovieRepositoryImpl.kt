package com.example.movieapp.data

import com.example.movieapp.data.datasource.MovieCacheDataSource
import com.example.movieapp.data.datasource.MovieLocalDataSource
import com.example.movieapp.data.datasource.MovieRemoteDataSource
import com.example.movieapp.data.model.Movie
import com.example.movieapp.domain.repository.MovieRepository

class MovieRepositoryImpl(
    val movieRemoteDataSource: MovieRemoteDataSource,
    val movieLocalDataSource: MovieLocalDataSource,
    val movieCacheDataSource: MovieCacheDataSource
):MovieRepository {
    override suspend fun getMovies(): List<Movie>? {
        return getMoviesFromCache()
    }



    override suspend fun updateMovies(): List<Movie>? {
       val listOfNewMovies= getMoviesFromApi()
        movieLocalDataSource.clearAll()
        movieLocalDataSource.saveMovieIntoDB(listOfNewMovies)
        movieCacheDataSource.saveMovieToCache(listOfNewMovies)
        return listOfNewMovies
    }

    suspend fun getMoviesFromApi(): List<Movie> {
        lateinit var movieList: List<Movie>

        try {
            val response=movieRemoteDataSource.getMovies()
            val body=response.body()
            if (body!=null){
                movieList=body.movies
            }
        }catch (_: java.lang.Exception){

        }
        return movieList
    }

    suspend fun getMoviesFromRoom():List<Movie>{
        lateinit var movieList: List<Movie>

        try {
            val response=movieLocalDataSource.getMoviesFromDB()
            movieList=response
        }catch (_:Exception){

        }
        if (movieList.size>0){
            return movieList
        }else{
            movieList=getMoviesFromApi()
            movieLocalDataSource.saveMovieIntoDB(movieList)
        }
        return movieList
    }


    private suspend fun getMoviesFromCache(): List<Movie>? {
        lateinit var movieList: List<Movie>

        try {
            val response=movieCacheDataSource.getMoviesFromCache()
            movieList=response
        }catch (_:Exception){

        }
        if (movieList.size>0){
            return movieList
        }else{
            movieList=getMoviesFromRoom()
            movieCacheDataSource.saveMovieToCache(movieList)
        }
        return movieList
    }
}