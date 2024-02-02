package com.example.movieapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.movieapp.data.model.Movie
import com.example.movieapp.data.model.MovieTitle
import com.example.movieapp.data.model.MovieTitleTypeConverter
import com.example.movieapp.data.model.People


@Database(entities = [Movie::class,People::class], version = 1)
@TypeConverters(MovieTitleTypeConverter::class)
abstract class TMDBDatabase : RoomDatabase(){

abstract fun movieDao():MyDao

}