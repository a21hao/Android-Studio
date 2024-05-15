package com.a21hao.myproject_monster_hunter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MonstersActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MonsterAdapter
    private lateinit var db: MonsterDatabase // Declaración de la base de datos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_monster)

        recyclerView = findViewById(R.id.monster_recycle)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MonsterAdapter(emptyList())
        recyclerView.adapter = adapter

        // Inicializa la base de datos
        db = Room.databaseBuilder(
            applicationContext,
            MonsterDatabase::class.java, "monster-database"
        ).build()

        // Obtiene los monstruos de la base de datos
        Thread {
            val monsters = db.monsterDao().getAll()
            runOnUiThread {
                adapter.setData(monsters)
            }
        }.start()

        // Realiza la llamada a la API para obtener los monstruos
        fetchDataFromApi()
    }

    private fun fetchDataFromApi() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://mhw-db.com/monsters/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)

        val call = apiService.getMonsters()
        call.enqueue(object : Callback<List<MonsterEntity>> {
            override fun onResponse(call: Call<List<MonsterEntity>>, response: Response<List<MonsterEntity>>) {
                if (response.isSuccessful) {
                    val monsters = response.body()!!.map { monster ->
                        MonsterEntity(
                            monster.id,
                            monster.type,
                            monster.species,
                            monster.name,
                            monster.description
                        )
                    }

                    Thread {
                        db.monsterDao().insertAll(monsters)
                    }.start()

                    adapter.setData(monsters)
                } else {
                    // Manejar errores de respuesta inesperada
                }
            }

            override fun onFailure(call: Call<List<MonsterEntity>>, t: Throwable) {
                // Manejar errores de comunicación (p. ej., problemas de red)
            }
        })
    }
}

