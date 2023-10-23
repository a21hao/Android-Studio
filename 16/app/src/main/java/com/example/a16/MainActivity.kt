package com.example.a16

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonGreen: Button = findViewById(R.id.button)
        buttonGreen.setOnClickListener() {
            val i = Intent(this, MainActivity2::class.java)
            i.putExtra("color", "green")
            startActivity(i)
        }
        val buttonBlue: Button = findViewById(R.id.button2)
        buttonBlue.setOnClickListener() {
            val i = Intent(this, MainActivity3:: class.java)
            i.putExtra("principal", "blue")
            startActivity(i)
        }
        val buttonRed: Button = findViewById(R.id.button3)
        buttonRed.setOnClickListener() {
            val i = Intent(this, MainActivity4:: class.java)
            i.putExtra("casa", "red")
            startActivity(i)
        }
    }
}