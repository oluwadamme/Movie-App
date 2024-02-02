package com.example.movieapp.presentation.di

import com.example.movieapp.domain.repository.Repository
import com.example.movieapp.domain.usecases.GetMethodUseCase
import com.example.movieapp.domain.usecases.UpdateMoviesUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {
    @Provides
    fun providesGetMoviesUseCase(repository: Repository):GetMethodUseCase{
        return GetMethodUseCase(repository)
    }

    @Provides
    fun providesUpdateMoviesUseCase(repository: Repository): UpdateMoviesUseCase {
        return UpdateMoviesUseCase(repository)
    }
}