package com.ingenesys.kotlin202201

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        obtenerYMostrarNombre()
        butRegresar.setOnClickListener { onBackPressed() }
    }

    fun obtenerYMostrarNombre(){
        val bundle = intent.extras
        val name = bundle?.get("name")
        tvNombrePersona.text = getString(R.string.bienvenido, name)
    }
}