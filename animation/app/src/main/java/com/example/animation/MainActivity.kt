package com.example.animation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val animationView = findViewById<LottieAnimationView>(R.id.animationView)
        animationView.setAnimation(R.raw.cohete)
        animationView.repeatCount = LottieDrawable.INFINITE
        animationView.playAnimation()
    }
}