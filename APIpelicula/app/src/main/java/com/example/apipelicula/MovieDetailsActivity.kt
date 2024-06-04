package com.example.apipelicula

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_movie_details.*
import com.example.apipelicula.R


class MovieDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        // Recuperar los datos de la película de la intención
        val movie = intent.getSerializableExtra("movie") as Movie

        // Asignar datos de la película a las vistas
        movieTitleLarge.text = movie.title
        movieDescription.text = movie.overview
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500${movie.posterPath}")
            .into(movieImageLarge)
    }
}
