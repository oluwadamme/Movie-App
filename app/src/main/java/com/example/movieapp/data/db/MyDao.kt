package com.example.movieapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movieapp.data.model.Movie
import com.example.movieapp.data.model.People

@Dao
interface MyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovies(movies: List<Movie>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePeople(people: List<People>)

    @Query("DELETE FROM popular_movies")
    suspend fun deleteAllMovies()

    @Query("DELETE FROM popular_people")
    suspend fun deleteAllPeople()

    @Query("SELECT * FROM popular_movies")
    suspend fun getMovies():List<Movie>

    @Query("SELECT * FROM popular_people")
    suspend fun getPeople():List<People>
}