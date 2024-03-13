package com.example.projectsimon

import android.media.AudioAttributes
import android.media.SoundPool
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    private lateinit var soundPool: SoundPool
    private var soundIds = intArrayOf()

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

        val attributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_GAME)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()

        soundPool = SoundPool.Builder()
            .setMaxStreams(4)
            .setAudioAttributes(attributes)
            .build()

        soundIds = intArrayOf(
            soundPool.load(this, R.raw.verde, 1),
            soundPool.load(this, R.raw.rojo, 1),
            soundPool.load(this, R.raw.amarillo, 1),
            soundPool.load(this, R.raw.azul, 1)
        )

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
        if (sequenceCount < 10) {
            val randomButton = (1..4).random()
            simonSequence.add(randomButton)

            job = CoroutineScope(Dispatchers.Main).launch {
                for (button in simonSequence) {
                    delay(700)
                    playButtonSound(button)
                    delay(1000)
                }
                userTurn = true
            }
        } else {
            Toast.makeText(this, "¡Has ganado!", Toast.LENGTH_SHORT).show()
        }
    }


    private fun playButtonSound(button: Int) {
        soundPool.play(soundIds[button - 1], 1.0f, 1.0f, 1, 0, 1.0f)
    }

    private fun playButtonSequence(button: Int) {
        if (userTurn || !simonSequence.isEmpty()) {
            playButtonSound(button)

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
    }


    private fun updateSequenceCountText() {
        sequenceCountTextView.text = "$sequenceCount"
    }

    override fun onDestroy() {
        super.onDestroy()
        job?.cancel()
        soundPool.release()
    }
}
