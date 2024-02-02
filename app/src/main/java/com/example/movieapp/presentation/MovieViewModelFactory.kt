package com.example.movieapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movieapp.domain.usecases.GetMethodUseCase
import com.example.movieapp.domain.usecases.UpdateMoviesUseCase

class MovieViewModelFactory(private val getMethodUseCase: GetMethodUseCase, private val updateMoviesUseCase: UpdateMoviesUseCase):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MovieViewModel(getMethodUseCase,updateMoviesUseCase) as T
    }
}