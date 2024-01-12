package com.example.movieapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movieapp.data.model.Movie


@Database(entities = [Movie::class], version = 1)
abstract class TMDBDatabase : RoomDatabase(){

abstract fun movieDao():MovieDao

}