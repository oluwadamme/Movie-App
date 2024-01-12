package com.example.movieapp.presentation.di

import com.example.movieapp.domain.usecases.GetMoviesUseCase
import com.example.movieapp.domain.usecases.UpdateMoviesUseCase
import com.example.movieapp.presentation.MovieViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class MovieModule {
    @MovieScope
    @Provides
    fun providesMovieViewModelFactory(getMoviesUseCase: GetMoviesUseCase, updateMoviesUseCase: UpdateMoviesUseCase):MovieViewModelFactory{
        return MovieViewModelFactory(getMoviesUseCase,updateMoviesUseCase)
    }

}