package com.example.apipelicula

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface MovieAPIService {
    @Headers(
        "Accept: application/json",
        "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJlMWE3NDRkZTM3NzQ2M2VkNWFkYmY0NTk4NjllNGNhZSIsInN1YiI6IjY1YTdmOTc3NTFjMDFmMDEzMTYwYzlkYSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.2WVlwVngNDFwzpYsxRIpksPwxrQqfhTxSe1JjbJmjkg"
    )
    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("language") language: String,
        @Query("page") page: Int,
    ): MovieResponse
}
