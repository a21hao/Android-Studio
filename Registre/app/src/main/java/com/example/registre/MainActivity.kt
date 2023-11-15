package com.example.registre

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)

        button.setOnClickListener {
            Log.d("MainActivity", "Registrat")
            Toast.makeText(this@MainActivity, "Registrat", Toast.LENGTH_SHORT).show();
        }
    }
}