package com.example.movieapp.presentation.di

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    NetModule::class,
    DatabaseModule::class,
    CacheDataSourceModule::class,
    LocalDataSourceModule::class,
    RemoteDataSourceModule::class,
    RepositoryModule::class,
    UseCaseModule::class,
MovieFragmentModule::class
])
interface AppComponent {
    fun movieSubComponent():MovieSubComponent.Factory
}