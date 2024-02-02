package com.example.movieapp.presentation.di

import com.example.movieapp.data.datasource.LocalDataSource
import com.example.movieapp.data.datasourceimpl.LocalDataSourceImpl
import com.example.movieapp.data.db.TMDBDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
@Module
class LocalDataSourceModule {
    @Singleton
    @Provides
    fun providesMovieLocalDataSource(tmdbDatabase: TMDBDatabase): LocalDataSource {
        return LocalDataSourceImpl(tmdbDatabase.movieDao())
    }
}