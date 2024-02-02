package com.example.movieapp.presentation.di

import com.example.movieapp.domain.usecases.GetMethodUseCase
import com.example.movieapp.domain.usecases.UpdateMoviesUseCase
import com.example.movieapp.presentation.MovieViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class MovieModule {
    @MovieScope
    @Provides
    fun providesMovieViewModelFactory(getMethodUseCase: GetMethodUseCase, updateMoviesUseCase: UpdateMoviesUseCase):MovieViewModelFactory{
        return MovieViewModelFactory(getMethodUseCase,updateMoviesUseCase)
    }

}