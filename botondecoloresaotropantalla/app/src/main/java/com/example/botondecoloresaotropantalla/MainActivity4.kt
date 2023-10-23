package com.example.botondecoloresaotropantalla

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity4 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)
        var TextView: TextView = findViewById(R.id.textView2)
        val colorString = intent?.extras?.getString("casa").toString()
        TextView.text = colorString
    }
}