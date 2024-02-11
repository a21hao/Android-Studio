package com.a21hao.myproject_monster_hunter

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.a21hao.myproject_monster_hunter.Monsters_Activity
import com.a21hao.myproject_monster_hunter.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Botón 1: Ir a Pantalla de Monstruos
        findViewById<Button>(R.id.button1).setOnClickListener {
            startActivity(Intent(this, Monsters_Activity::class.java))
        }

        // Botón 2: Ir a Pantalla de Item
        findViewById<Button>(R.id.button2).setOnClickListener {
            startActivity(Intent(this, Items_Activity::class.java))
        }

        // Botón 3: Ir a Pantalla de Armas

        findViewById<Button>(R.id.button3).setOnClickListener {
            startActivity(Intent(this, Weapons_Activity::class.java))
        }

    }
}
