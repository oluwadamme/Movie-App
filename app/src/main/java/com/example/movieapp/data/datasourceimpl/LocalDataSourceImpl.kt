package com.example.movieapp.data.datasourceimpl

import com.example.movieapp.data.datasource.LocalDataSource
import com.example.movieapp.data.db.MyDao
import com.example.movieapp.data.model.Movie
import com.example.movieapp.data.model.People
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LocalDataSourceImpl(private val myDao: MyDao):LocalDataSource {
    override suspend fun getMoviesFromDB(): List<Movie> {
        return  myDao.getMovies()
    }

    override suspend fun saveMovieIntoDB(movies: List<Movie>) {
        CoroutineScope(Dispatchers.IO).launch {
            myDao.saveMovies(movies)
        }
    }

    override suspend fun getPeopleFromDB(): List<People> {
       return myDao.getPeople()
    }

    override suspend fun savePeopleIntoDB(people: List<People>) {
        CoroutineScope(Dispatchers.IO).launch {
            myDao.savePeople(people)
        }
    }

    override suspend fun clearAll() {
        CoroutineScope(Dispatchers.IO).launch {
            myDao.deleteAllMovies()
        }
    }
}