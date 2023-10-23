package com.example.botondecoloresaotropantalla

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        var TextView: TextView = findViewById(R.id.textView3)
        val colorString = intent?.extras?.getString("principal").toString()
        TextView.text = colorString
    }
}