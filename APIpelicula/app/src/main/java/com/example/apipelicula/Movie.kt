package com.example.apipelicula

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Movie(
    @SerializedName("title") val title: String,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("vote_average") val voteAverage: Float
) : Serializable
