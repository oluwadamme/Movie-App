package com.example.movieapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.movieapp.domain.usecases.GetMethodUseCase
import com.example.movieapp.domain.usecases.UpdateMoviesUseCase

class MovieViewModel(private val getMethodUseCase: GetMethodUseCase, private val updateMoviesUseCase: UpdateMoviesUseCase):
    ViewModel() {
    fun getMovies()=liveData{
        val movieList=getMethodUseCase.execute()
        emit(movieList)
    }

    fun getPeople(page:Int)=liveData{
        val people=getMethodUseCase.getPeople(page)
        emit(people)
    }
    fun updateMovies()=liveData{
        val movieList=updateMoviesUseCase.execute()
        emit(movieList)
    }
}