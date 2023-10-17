package com.example.prova

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    //lateinit var acceptButton: Button
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*acceptButton = findViewById(R.id.acceptButton)

        acceptButton.setOnClickListener {
            Log.d( tag: "Garaje", msg: "Click en boton" )
        }*/
        //Asignar un listener al botón para que al pulsarlo se abra el terminal
        button.setOnClickListener {
            //Crear un Intent con la acción de abrir el terminal
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("tel:") //Usar el esquema tel: para abrir el terminal
            //Verificar que haya una aplicación que pueda manejar el Intent
            if (intent.resolveActivity(packageManager) != null) {
                //Iniciar la actividad con el Intent
                startActivity(intent)
            }
        }
    }
}