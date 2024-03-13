package com.example.projectsimon

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class CanvasView : View {
    private var paint: Paint? = null
    private var canvasWidth = 0
    private var canvasHeight = 0
    private var squareSize = 0
    private var startX = 0
    private var startY = 0

    constructor(context: Context?) : super(context) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    private fun init() {
        paint = Paint()
        paint!!.isAntiAlias = true
        squareSize = 200 // Tamaño del cuadrado
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        canvasWidth = w
        canvasHeight = h
        startX = (canvasWidth - squareSize * 2) / 2
        startY = (canvasHeight - squareSize * 2) / 2
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // Dibuja los cuatro cuadrados en el centro del canvas
        paint!!.color = Color.BLUE
        canvas.drawRect(
            startX.toFloat(),
            startY.toFloat(),
            (startX + squareSize).toFloat(),
            (startY + squareSize).toFloat(),
            paint!!
        )
        paint!!.color = Color.RED
        canvas.drawRect(
            (startX + squareSize).toFloat(),
            startY.toFloat(),
            (startX + squareSize * 2).toFloat(),
            (startY + squareSize).toFloat(),
            paint!!
        )
        paint!!.color = Color.GREEN
        canvas.drawRect(
            startX.toFloat(),
            (startY + squareSize).toFloat(),
            (startX + squareSize).toFloat(),
            (startY + squareSize * 2).toFloat(),
            paint!!
        )
        paint!!.color = Color.YELLOW
        canvas.drawRect(
            (startX + squareSize).toFloat(),
            (startY + squareSize).toFloat(),
            (startX + squareSize * 2).toFloat(),
            (startY + squareSize * 2).toFloat(),
            paint!!
        )
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x.toInt()
        val y = event.y.toInt()
        when (event.action) {
            MotionEvent.ACTION_DOWN ->                 // Verifica si el toque está dentro de uno de los cuadrados
                if (x >= startX && x <= startX + squareSize * 2 && y >= startY && y <= startY + squareSize * 2) {
                    // Dependiendo del cuadrado tocado, puedes realizar acciones específicas aquí
                    if (x <= startX + squareSize && y <= startY + squareSize) {
                        // Se ha tocado el cuadrado azul
                        // Realiza las acciones necesarias para el cuadrado azul
                    } else if (x > startX + squareSize && y <= startY + squareSize) {
                        // Se ha tocado el cuadrado rojo
                        // Realiza las acciones necesarias para el cuadrado rojo
                    } else if (x <= startX + squareSize && y > startY + squareSize) {
                        // Se ha tocado el cuadrado verde
                        // Realiza las acciones necesarias para el cuadrado verde
                    } else {
                        // Se ha tocado el cuadrado amarillo
                        // Realiza las acciones necesarias para el cuadrado amarillo
                    }
                }
        }
        return true
    }
}
