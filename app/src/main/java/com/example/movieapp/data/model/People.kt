package com.example.movieapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken

@Entity(tableName = "popular_people")
data class People(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    val id: Int,

    val name: String,

    @SerializedName("profile_path")
    val image: String,

    @SerializedName("known_for")
    @TypeConverters(MovieTitleTypeConverter::class)
    val movies: List<MovieTitle>,

    )

data class MovieTitle(
    val title: String,
)

class MovieTitleTypeConverter {

    @TypeConverter
    fun fromJson(value: String): List<MovieTitle> {
        val listType = object : TypeToken<List<MovieTitle>>() {}.type

        return Gson().fromJson(value, listType)
    }


    @TypeConverter
    fun toJson(value: List<MovieTitle>): String {

        return Gson().toJson(value)
    }
}