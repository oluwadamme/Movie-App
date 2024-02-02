package com.example.movieapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "popular_people")
data class People(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    val id: Int,

    val name: String,
    @SerializedName("profile_path")
    val image: String,
    @SerializedName("known_for")
    val movies: List<MovieTitle>
)

data class MovieTitle(
    val title: String,
)