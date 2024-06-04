package com.example.apipelicula

import okhttp3.Response
import retrofit2.http.GET

interface MovieApiService {
    @GET("now_playing?api_key=e1a744de377463ed5adbf459869e4cae")
    suspend fun getNowPlayingMovies(): Response<MovieResponse>
}
