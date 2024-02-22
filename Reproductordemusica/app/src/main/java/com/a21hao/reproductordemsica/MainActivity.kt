package com.a21hao.reproductordemsica

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val songs = listOf(
        Song("平凡职业也能造就世界最强第二季ED", R.raw.arifureta),
        Song("进击的巨人", R.raw.attack_of_titans),
        Song("鬼灭之刃", R.raw.guardian_de_la_nit),
        Song("家庭教师", R.raw.rebon),
        Song("Redeem the Future", R.raw.redeem_the_future),
        Song("左手指月", R.raw.upwardsto_the_moon)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = SongAdapter(songs)
    }
}
