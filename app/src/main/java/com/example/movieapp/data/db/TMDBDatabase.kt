package com.example.movieapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movieapp.data.model.Movie
import com.example.movieapp.data.model.People


@Database(entities = [Movie::class,People::class], version = 1)
abstract class TMDBDatabase : RoomDatabase(){

abstract fun movieDao():MyDao

}