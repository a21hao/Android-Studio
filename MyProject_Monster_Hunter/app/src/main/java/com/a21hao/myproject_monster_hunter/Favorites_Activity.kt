package com.a21hao.myproject_monster_hunter

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Favorites_Activity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MonsterAdapter
    private var favoriteMonsters: MutableList<Monster> = mutableListOf()
    private val gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)

        recyclerView = findViewById(R.id.favorites_recycle)
        recyclerView.layoutManager = LinearLayoutManager(this)

        loadFavorites()

        adapter = MonsterAdapter(this, favoriteMonsters)
        recyclerView.adapter = adapter
    }

    private fun loadFavorites() {
        val sharedPreferences = getSharedPreferences("favorites", Context.MODE_PRIVATE)
        val json = sharedPreferences.getString("favorite_monsters", "")
        val type = object : TypeToken<MutableList<Monster>>() {}.type
        favoriteMonsters = if (json.isNullOrEmpty()) mutableListOf() else gson.fromJson(json, type)
    }
}
