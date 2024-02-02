package com.example.movieapp.domain.usecases

import com.example.movieapp.data.model.Movie
import com.example.movieapp.domain.repository.Repository

class UpdateMoviesUseCase(private val repository: Repository) {
    suspend fun execute():List<Movie>?=repository.updateMovies()
}