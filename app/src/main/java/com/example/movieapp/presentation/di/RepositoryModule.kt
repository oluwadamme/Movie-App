package com.example.movieapp.presentation.di

import com.example.movieapp.data.RepositoryImpl
import com.example.movieapp.data.datasource.CacheDataSource
import com.example.movieapp.data.datasource.LocalDataSource
import com.example.movieapp.data.datasource.RemoteDataSource
import com.example.movieapp.domain.repository.Repository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Singleton
    @Provides
    fun providesMoviesRepository(remoteDataSource: RemoteDataSource, localDataSource: LocalDataSource, cacheDataSource: CacheDataSource):Repository{
        return RepositoryImpl(remoteDataSource,localDataSource,cacheDataSource)
    }
}