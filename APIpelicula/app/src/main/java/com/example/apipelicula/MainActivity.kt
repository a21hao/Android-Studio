package com.example.apipelicula

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.apipelicula.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()
        loadMovies()
    }

    private fun setupRecyclerView() {
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        movieAdapter = MovieAdapter(emptyList()) { movie ->
            val intent = Intent(this, MovieDetailsActivity::class.java)
            intent.putExtra("movie", movie)
            startActivity(intent)
        }
        recyclerView.adapter = movieAdapter
    }

    private fun loadMovies() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(MovieAPIService::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = service.getTopRatedMovies(
                    language = "es-ES",
                    page = 1
                )

                if (response.results.isNotEmpty()) {
                    val movies = response.results.map { movie ->
                        Movie(
                            title = movie.title,
                            releaseDate = movie.releaseDate,
                            overview = movie.overview,
                            posterPath = movie.posterPath,
                            voteAverage = movie.voteAverage
                        )
                    }

                    withContext(Dispatchers.Main) {
                        movieAdapter.updateMovies(movies)
                    }
                }
            } catch (e: Exception) {
                Log.e("MainActivity", "Error loading movies: ${e.message}")
            }
        }
    }
}
