package com.example.botondecoloresaotropantalla

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        var TextView: TextView = findViewById(R.id.textView)
        val colorString = intent?.extras?.getString("color").toString()
        TextView.text = colorString

    }
}