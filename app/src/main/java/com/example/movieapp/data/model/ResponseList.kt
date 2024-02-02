package com.example.movieapp.data.model

import com.google.gson.annotations.SerializedName

data class MovieList(
    @SerializedName("results")
    val movies:List<Movie>
)
data class PeopleList(
    @SerializedName("results")
    val movies:List<People>
)
