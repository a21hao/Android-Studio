package com.a21hao.myproject_monster_hunter

import android.os.Bundle
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
    private var weapons: MutableList<Weapon> = mutableListOf()
    private lateinit var adapter: WeaponsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weapons)

        recyclerView = findViewById(R.id.weapons_recycle)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = WeaponsAdapter(this, weapons)
        recyclerView.adapter = adapter

        val retrofit = Retrofit.Builder()
            .baseUrl("https://mhw-db.com/") // Corrige la URL base
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(WeaponsApiService::class.java) // Utiliza WeaponsApiService

        val call = apiService.getWeapons()
        call.enqueue(object : Callback<List<Weapon>> {
            override fun onResponse(call: Call<List<Weapon>>, response: Response<List<Weapon>>) {
                if (response.isSuccessful) {
                    weapons.addAll(response.body()!!)
                    adapter.notifyDataSetChanged()
                } else {
                    // Manejar errores
                }
            }

            override fun onFailure(call: Call<List<Weapon>>, t: Throwable) {
                // Manejar errores
            }
        })
    }
}
