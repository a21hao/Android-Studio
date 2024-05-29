package com.example.prueba

import android.net.Uri
import android.os.Bundle
import android.widget.MediaController
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Referencias a los VideoView en el diseño
        val videoViewOnline: VideoView = findViewById(R.id.videoViewOnline)
        val videoViewLocal: VideoView = findViewById(R.id.videoViewLocal)

        // Configurar la URL del video en línea
        val videoUrlOnline = "https://www.dailymotion.com/video/x8w6p8c"
        val videoUriOnline = Uri.parse(videoUrlOnline)
        videoViewOnline.setVideoURI(videoUriOnline)

        // Configurar el video local, que debe estar en la carpeta "raw" del proyecto
        val videoPathLocal =
            "android.resource://" + packageName + "/" + R.raw.video
        val videoUriLocal = Uri.parse(videoPathLocal)
        videoViewLocal.setVideoURI(videoUriLocal)

        // Añadir controles de reproducción
        val mediaControllerOnline = MediaController(this)
        videoViewOnline.setMediaController(mediaControllerOnline)

        val mediaControllerLocal = MediaController(this)
        videoViewLocal.setMediaController(mediaControllerLocal)

        // Comenzar la reproducción de los videos
        videoViewOnline.start()
        videoViewLocal.start()
    }
}
