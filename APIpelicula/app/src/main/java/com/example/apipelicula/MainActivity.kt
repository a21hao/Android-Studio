package com.example.apipelicula

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.apipelicula.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.apipelicula.R


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        movieAdapter = MovieAdapter(emptyList()) { movie ->
            // Handle movie click
            val intent = Intent(this, MovieDetailsActivity::class.java)
            intent.putExtra("movie", movie)
            startActivity(intent)
        }
        recyclerView.adapter = movieAdapter

        loadData()
    }

    private fun loadData() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/movie/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(MovieApiService::class.java)
        GlobalScope.launch(Dispatchers.IO) {
            val response = service.getNowPlayingMovies()
            if (response.isSuccessful) {
                val movies = response.body()?.results ?: emptyList()
                withContext(Dispatchers.Main) {
                    movieAdapter.movies = movies
                    movieAdapter.notifyDataSetChanged()
                }
            } else {
                // Handle error
            }
        }
    }
}
