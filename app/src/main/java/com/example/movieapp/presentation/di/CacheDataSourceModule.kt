package com.example.movieapp.presentation.di

import com.example.movieapp.data.datasource.MovieCacheDataSource
import com.example.movieapp.data.datasource.MovieLocalDataSource
import com.example.movieapp.data.datasourceimpl.MovieCacheDataSourceImpl
import com.example.movieapp.data.datasourceimpl.MovieLocalDataSourceImpl
import com.example.movieapp.data.db.TMDBDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheDataSourceModule {
    @Singleton
    @Provides
    fun providesMovieLocalDataSource(): MovieCacheDataSource {
        return MovieCacheDataSourceImpl()
    }
}