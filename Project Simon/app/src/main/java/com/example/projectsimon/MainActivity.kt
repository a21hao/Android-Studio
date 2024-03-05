package com.example.projectsimon

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    private lateinit var mediaPlayer1: MediaPlayer
    private lateinit var mediaPlayer2: MediaPlayer
    private lateinit var mediaPlayer3: MediaPlayer
    private lateinit var mediaPlayer4: MediaPlayer

    private lateinit var sequenceCountTextView: TextView

    private val simonSequence = mutableListOf<Int>()
    private var playerSequence = mutableListOf<Int>()
    private var userTurn = false
    private var currentIndex = 0
    private var sequenceCount = 0

    private var job: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sequenceCountTextView = findViewById(R.id.sequenceCountTextView)

        mediaPlayer1 = MediaPlayer.create(this, R.raw.verde)
        mediaPlayer2 = MediaPlayer.create(this, R.raw.rojo)
        mediaPlayer3 = MediaPlayer.create(this, R.raw.amarillo)
        mediaPlayer4 = MediaPlayer.create(this, R.raw.azul)

        val button1: Button = findViewById(R.id.button)
        val button2: Button = findViewById(R.id.button2)
        val button3: Button = findViewById(R.id.button3)
        val button4: Button = findViewById(R.id.button4)

        val playButton: Button = findViewById(R.id.play)
        playButton.setOnClickListener { startGame() }

        button1.setOnClickListener { playButtonSequence(1) }
        button2.setOnClickListener { playButtonSequence(2) }
        button3.setOnClickListener { playButtonSequence(3) }
        button4.setOnClickListener { playButtonSequence(4) }
    }

    private fun startGame() {
        simonSequence.clear()
        playerSequence.clear()
        userTurn = false
        currentIndex = 0
        sequenceCount = 0
        updateSequenceCountText()

        addToSimonSequence()
    }

    private fun addToSimonSequence() {
        val randomButton = (1..4).random()
        simonSequence.add(randomButton)

        job = CoroutineScope(Dispatchers.Main).launch {
            for (button in simonSequence) {
                playButtonSound(button)
                delay(1000)
            }
            userTurn = true
        }
    }

    private suspend fun playButtonSound(button: Int) {
        val mediaPlayer = when (button) {
            1 -> mediaPlayer1
            2 -> mediaPlayer2
            3 -> mediaPlayer3
            else -> mediaPlayer4
        }
        mediaPlayer.seekTo(0)
        mediaPlayer.start()
    }

    private fun playButtonSequence(button: Int) {
        if (userTurn) {
            playerSequence.add(button)
            if (button != simonSequence[currentIndex]) {
                startGame()
            } else {
                currentIndex++
                if (currentIndex == simonSequence.size) {
                    userTurn = false
                    currentIndex = 0
                    sequenceCount++
                    updateSequenceCountText()
                    addToSimonSequence()
                }
            }
        }
    }

    private fun updateSequenceCountText() {
        sequenceCountTextView.text = "$sequenceCount"
    }

    override fun onDestroy() {
        super.onDestroy()
        job?.cancel()
        mediaPlayer1.release()
        mediaPlayer2.release()
        mediaPlayer3.release()
        mediaPlayer4.release()
    }
}
