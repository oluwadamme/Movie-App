package com.example.movieapp.presentation.di

import com.example.movieapp.data.datasource.CacheDataSource
import com.example.movieapp.data.datasourceimpl.CacheDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheDataSourceModule {
    @Singleton
    @Provides
    fun providesMovieLocalDataSource(): CacheDataSource {
        return CacheDataSourceImpl()
    }
}