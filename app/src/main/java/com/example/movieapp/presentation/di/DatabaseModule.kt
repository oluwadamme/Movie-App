package com.example.movieapp.presentation.di

import android.content.Context
import androidx.room.Room
import com.example.movieapp.data.db.MyDao
import com.example.movieapp.data.db.TMDBDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun providesMovieDatabase(context: Context):TMDBDatabase{
        return Room.databaseBuilder(context,TMDBDatabase::class.java,"tmdbclient").build()
    }

    @Singleton
    @Provides
    fun providesMovieDao(tmdbDatabase: TMDBDatabase):MyDao{
        return tmdbDatabase.movieDao()
    }
}