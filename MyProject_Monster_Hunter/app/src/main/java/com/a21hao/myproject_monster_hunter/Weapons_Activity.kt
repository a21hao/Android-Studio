package com.a21hao.myproject_monster_hunter

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Weapons_Activity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: View
    private var weapons: MutableList<Weapon> = mutableListOf()
    private lateinit var adapter: WeaponsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weapons)

        recyclerView = findViewById(R.id.weapons_recycle)
        progressBar = findViewById(R.id.progress_bar2)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = WeaponsAdapter(this, weapons)
        recyclerView.adapter = adapter

        progressBar.visibility = View.VISIBLE
        recyclerView.visibility = View.GONE

        val retrofit = Retrofit.Builder()
            .baseUrl("https://mhw-db.com/weapons/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(WeaponsApiService::class.java)

        val call = apiService.getWeapons()
        call.enqueue(object : Callback<List<Weapon>> {
            override fun onResponse(call: Call<List<Weapon>>, response: Response<List<Weapon>>) {
                if (response.isSuccessful) {
                    weapons.addAll(response.body()!!)
                    adapter.notifyDataSetChanged()

                    progressBar.visibility = View.GONE
                    recyclerView.visibility = View.VISIBLE
                } else {
                    progressBar.visibility = View.GONE
                }
            }

            override fun onFailure(call: Call<List<Weapon>>, t: Throwable) {
                progressBar.visibility = View.GONE
            }
        })
    }
}
