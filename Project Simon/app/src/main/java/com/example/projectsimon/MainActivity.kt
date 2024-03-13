package com.example.projectsimon

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var canvasView: CanvasView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        canvasView = findViewById(R.id.canvasView)

        val button1: Button = findViewById(R.id.button)
        val button2: Button = findViewById(R.id.button2)
        val button3: Button = findViewById(R.id.button3)
        val button4: Button = findViewById(R.id.button4)
        val playButton: Button = findViewById(R.id.play)

        button1.setOnClickListener { canvasView.onButtonClick(1) }
        button2.setOnClickListener { canvasView.onButtonClick(2) }
        button3.setOnClickListener { canvasView.onButtonClick(3) }
        button4.setOnClickListener { canvasView.onButtonClick(4) }

        playButton.setOnClickListener {
            canvasView.startGame()
        }
    }
}
