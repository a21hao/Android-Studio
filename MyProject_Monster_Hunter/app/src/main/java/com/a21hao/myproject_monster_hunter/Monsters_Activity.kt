package com.a21hao.myproject_monster_hunter

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Monsters_Activity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: View
    private lateinit var animatedImage: ImageView
    private var monsters: MutableList<Monster> = mutableListOf()
    private lateinit var adapter: MonsterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_monster)

        recyclerView = findViewById(R.id.monster_recycle)
        progressBar = findViewById(R.id.progress_bar)
        animatedImage = findViewById(R.id.animated_image)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MonsterAdapter(monsters)
        recyclerView.adapter = adapter

        startAnimation()

        progressBar.visibility = View.VISIBLE
        recyclerView.visibility = View.GONE

        val retrofit = Retrofit.Builder()
                .baseUrl("https://mhw-db.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val apiService = retrofit.create(ApiService::class.java)

        val call = apiService.getMonsters()
        call.enqueue(object : Callback<List<Monster>> {
            override fun onResponse(call: Call<List<Monster>>, response: Response<List<Monster>>) {
                if (response.isSuccessful) {
                    monsters.addAll(response.body()!!)
                    adapter.notifyDataSetChanged()

                    progressBar.visibility = View.GONE
                    recyclerView.visibility = View.VISIBLE
                    animatedImage.visibility = View.GONE
                } else {
                    progressBar.visibility = View.GONE
                }
            }

            override fun onFailure(call: Call<List<Monster>>, t: Throwable) {
                progressBar.visibility = View.GONE
            }
        })
    }

    private fun startAnimation() {
        val scaleX = ObjectAnimator.ofFloat(animatedImage, "scaleX", 1.0f, 1.5f, 1.0f)
        scaleX.duration = 1000
        scaleX.repeatCount = ObjectAnimator.INFINITE
        scaleX.repeatMode = ObjectAnimator.REVERSE

        val scaleY = ObjectAnimator.ofFloat(animatedImage, "scaleY", 1.0f, 1.5f, 1.0f)
        scaleY.duration = 1000
        scaleY.repeatCount = ObjectAnimator.INFINITE
        scaleY.repeatMode = ObjectAnimator.REVERSE

        val alphaAnimator = ObjectAnimator.ofFloat(animatedImage, "alpha", 1.0f, 0.5f, 1.0f)
        alphaAnimator.duration = 1000
        alphaAnimator.repeatCount = ObjectAnimator.INFINITE
        alphaAnimator.repeatMode = ObjectAnimator.REVERSE

        val animatorSet = AnimatorSet()
        animatorSet.playTogether(scaleX, scaleY, alphaAnimator)
        animatorSet.start()
    }
}
