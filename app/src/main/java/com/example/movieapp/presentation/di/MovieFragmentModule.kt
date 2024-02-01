package com.example.movieapp.presentation.di

import android.content.Context
import androidx.room.Room
import com.example.movieapp.data.db.TMDBDatabase
import com.example.movieapp.presentation.MovieViewModelFactory
import com.example.movieapp.presentation.fragments.MovieFragment
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
@Module
class MovieFragmentModule {
    @Singleton
    @Provides
    fun providesMovieFragment(factory: MovieViewModelFactory): MovieFragment {
        return MovieFragment(factory)
    }
}