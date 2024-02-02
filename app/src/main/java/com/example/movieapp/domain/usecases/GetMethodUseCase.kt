package com.example.movieapp.domain.usecases

import com.example.movieapp.data.model.Movie
import com.example.movieapp.data.model.People
import com.example.movieapp.domain.repository.Repository

class GetMethodUseCase(private val repository: Repository) {
    suspend fun execute():List<Movie>?=repository.getMovies()
    suspend fun getPeople():List<People>?=repository.getPeople()
}