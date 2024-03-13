package com.example.projectsimon

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.media.AudioAttributes
import android.media.SoundPool
import android.util.AttributeSet
import android.view.View
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CanvasView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private val paint = Paint()
    private val button1Color = Color.parseColor("#4CAF50")
    private val button2Color = Color.parseColor("#FF0000")
    private val button3Color = Color.parseColor("#FFB13B")
    private val button4Color = Color.parseColor("#2196F3")
    private val textColor = Color.BLACK
    private val buttonSize = 100 // Tamaño de los botones
    private val buttonMargin = 5 // Margen entre los botones
    private val textMarginTop = 67 // Margen superior del texto "Simon"
    private val buttonContainerMarginTop = 200 // Margen superior de los contenedores de botones
    private val buttonContainerWidth = 100 // Ancho de los contenedores de botones
    private val buttonContainerHeight = 200 // Alto de los contenedores de botones
    private val playButtonMarginTop = 250 // Margen superior del botón "Play"
    private val sequenceCountTextMarginBottom = 500 // Margen inferior del texto de conteo de secuencia

    private var sequenceCount = 0
    private var gameStarted = false
    private var userTurn = false
    private val simonSequence = mutableListOf<Int>()
    private val playerSequence = mutableListOf<Int>()
    private var currentIndex = 0
    private var soundPool: SoundPool? = null
    private val soundIds = intArrayOf(
        R.raw.verde,
        R.raw.rojo,
        R.raw.amarillo,
        R.raw.azul
    )
    private var job: Job? = null

    init {
        initializeSoundPool()
    }

    private fun initializeSoundPool() {
        val attributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_GAME)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()

        soundPool = SoundPool.Builder()
            .setMaxStreams(4)
            .setAudioAttributes(attributes)
            .build()

        soundIds.forEach { soundId ->
            soundPool?.load(context, soundId, 1)
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // Dibujar los botones
        drawButton(canvas, 0, buttonContainerMarginTop, button1Color)
        drawButton(canvas, buttonContainerWidth + buttonMargin, buttonContainerMarginTop, button2Color)
        drawButton(canvas, 0, buttonContainerMarginTop + buttonContainerHeight + buttonMargin, button3Color)
        drawButton(canvas, buttonContainerWidth + buttonMargin, buttonContainerMarginTop + buttonContainerHeight + buttonMargin, button4Color)

        // Dibujar el texto "Simon"
        paint.textSize = 48f
        paint.color = textColor
        canvas.drawText("Simon", (width - paint.measureText("Simon")) / 2, textMarginTop.toFloat(), paint)

        // Dibujar el texto de conteo de secuencia
        paint.textSize = 24f
        canvas.drawText(sequenceCount.toString(), (width - paint.measureText(sequenceCount.toString())) / 2, (height - sequenceCountTextMarginBottom).toFloat(), paint)

        // Dibujar el botón "Play"
        drawButton(canvas, (width - buttonContainerWidth) / 2, playButtonMarginTop, Color.BLACK)
        paint.textSize = 48f
        paint.color = Color.WHITE
        canvas.drawText("Play", (width - paint.measureText("Play")) / 2, playButtonMarginTop.toFloat() + buttonSize / 2, paint)
    }

    private fun drawButton(canvas: Canvas, x: Int, y: Int, color: Int) {
        paint.color = color
        canvas.drawRect(x.toFloat(), y.toFloat(), (x + buttonSize).toFloat(), (y + buttonSize).toFloat(), paint)
    }

    fun startGame() {
        sequenceCount = 0
        gameStarted = true
        invalidate()
        addToSimonSequence()
    }

    private fun addToSimonSequence() {
        if (sequenceCount < 10) {
            val randomButton = (1..4).random()
            simonSequence.add(randomButton)

            job = CoroutineScope(Dispatchers.Main).launch {
                for (button in simonSequence) {
                    delay(1000)
                    playButtonSound(button)
                    delay(1000)
                }
                userTurn = true
            }
        } else {
            Toast.makeText(context, "¡Has ganado!", Toast.LENGTH_SHORT).show()
            gameStarted = false
        }
    }

    private fun playButtonSound(button: Int) {
        soundPool?.play(soundIds[button - 1], 1.0f, 1.0f, 1, 0, 1.0f)
    }

    fun onButtonClick(buttonNumber: Int) {
        if (gameStarted && userTurn) {
            playButtonSound(buttonNumber)
            playerSequence.add(buttonNumber)
            checkPlayerSequence()
        }
    }

    private fun checkPlayerSequence() {
        if (playerSequence[currentIndex] == simonSequence[currentIndex]) {
            currentIndex++
            if (currentIndex == simonSequence.size) {
                userTurn = false
                currentIndex = 0
                sequenceCount++
                addToSimonSequence()
            }
        } else {
            Toast.makeText(context, "¡Incorrecto! Juego terminado.", Toast.LENGTH_SHORT).show()
            resetGame()
        }
    }

    private fun resetGame() {
        gameStarted = false
        simonSequence.clear()
        playerSequence.clear()
        sequenceCount = 0
        userTurn = false
        invalidate()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        job?.cancel()
        soundPool?.release()
    }
}
