package com.example.movieapp.presentation.di

import com.example.movieapp.data.api.TMDBService
import com.example.movieapp.data.datasource.RemoteDataSource
import com.example.movieapp.data.datasourceimpl.RemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RemoteDataSourceModule(private val apiKey:String) {

    @Singleton
    @Provides
    fun providesMovieRemoteDataSource(tmdbService: TMDBService):RemoteDataSource{
        return RemoteDataSourceImpl(tmdbService, apiKey)
    }
}