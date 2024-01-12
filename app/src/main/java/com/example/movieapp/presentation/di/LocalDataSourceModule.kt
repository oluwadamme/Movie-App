package com.example.movieapp.presentation.di

import com.example.movieapp.data.api.TMDBService
import com.example.movieapp.data.datasource.MovieLocalDataSource
import com.example.movieapp.data.datasource.MovieRemoteDataSource
import com.example.movieapp.data.datasourceimpl.MovieLocalDataSourceImpl
import com.example.movieapp.data.datasourceimpl.MovieRemoteDataSourceImpl
import com.example.movieapp.data.db.TMDBDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
@Module
class LocalDataSourceModule {
    @Singleton
    @Provides
    fun providesMovieLocalDataSource(tmdbDatabase: TMDBDatabase): MovieLocalDataSource {
        return MovieLocalDataSourceImpl(tmdbDatabase.movieDao())
    }
}