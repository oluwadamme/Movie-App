package com.example.movieapp.domain.repository

import com.example.movieapp.data.model.Movie
import com.example.movieapp.data.model.People
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getMovies():List<Movie>?
    suspend fun updateMovies():List<Movie>?

    suspend fun getPeople(page:Int):List<People>?
}