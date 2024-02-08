package com.a21hao.myproject_monster_hunter

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private var monsters: MutableList<Monster> = mutableListOf()
    private lateinit var adapter: MonsterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        recyclerView = findViewById(R.id.monster_recycle)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MonsterAdapter(monsters)
        recyclerView.adapter = adapter

        val retrofit = Retrofit.Builder()
            .baseUrl("https://tu-api-url.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)

        val call = apiService.getMonsters()
        call.enqueue(object : Callback<List<Monster>> {
            override fun onResponse(call: Call<List<Monster>>, response: Response<List<Monster>>) {
                if (response.isSuccessful) {
                    monsters.addAll(response.body()!!)
                    adapter.notifyDataSetChanged()
                } else {
                    // Manejar errores
                }
            }

            override fun onFailure(call: Call<List<Monster>>, t: Throwable) {
                // Manejar errores
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_item1 -> {
                // Acción para el primer botón del menú
                true
            }
            R.id.menu_item2 -> {
                // Acción para el segundo botón del menú
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
