package com.example.movieapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.movieapp.domain.usecases.GetMoviesUseCase
import com.example.movieapp.domain.usecases.UpdateMoviesUseCase

class MovieViewModel(private val getMoviesUseCase: GetMoviesUseCase,private val updateMoviesUseCase: UpdateMoviesUseCase):
    ViewModel() {
    fun getMovies()=liveData{
        val movieList=getMoviesUseCase.execute()
        emit(movieList)
    }

    fun updateMovies()=liveData{
        val movieList=updateMoviesUseCase.execute()
        emit(movieList)
    }
}