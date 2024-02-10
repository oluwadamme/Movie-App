package com.example.movieapp.data.model

import com.google.gson.annotations.SerializedName

data class MovieList(
    @SerializedName("results")
    val movies:List<Movie>,

)
data class PeopleList(
    @SerializedName("results")
    val movies:List<People>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int,
    val page: Int,
)
