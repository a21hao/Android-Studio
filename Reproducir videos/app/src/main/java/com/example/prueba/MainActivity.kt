package com.example.prueba

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.widget.MediaController
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val videoViewLocal: VideoView = findViewById(R.id.videoViewLocal)

        val videoPathLocal =
            "android.resource://" + packageName + "/" + R.raw.mariovideo
        val videoUriLocal = Uri.parse(videoPathLocal)
        videoViewLocal.setVideoURI(videoUriLocal)

        val mediaControllerLocal = MediaController(this)
        videoViewLocal.setMediaController(mediaControllerLocal)

        videoViewLocal.setOnPreparedListener {
            videoViewLocal.start()
        }

        mediaPlayer?.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
    }
}
