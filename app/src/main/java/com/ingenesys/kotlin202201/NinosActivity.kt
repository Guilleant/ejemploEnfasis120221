package com.ingenesys.kotlin202201

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_ninos.*


class NinosActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ninos )

        val spinner: Spinner = findViewById(R.id.spinner)
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.tipos_identificacion,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }

        spinner.onItemSelectedListener = this

        btnGuardar.setOnClickListener {
            if (etIdentificacion.text.toString().length > 0 &&
                etApellidos.text.toString().length > 0 &&
                etNombres.text.toString().length > 0 &&
                etDireccion.text.toString().length > 0 &&
                etTelefono.text.toString().length > 0
            ) {

                val tipoId = spinner.selectedItem.toString()

                var nino = ClsNinos(
                    tipoId,
                    etIdentificacion.text.toString().toInt(),
                    etApellidos.text.toString(),
                    etNombres.text.toString(),
                    etDireccion.text.toString(),
                    etTelefono.text.toString()
                )

                var db = DatabaseHandler(this)
                var id_registro = db.insertData(nino)

                if( id_registro > 0 ) {
                    limpiar()
                }
            } else {
                Toast.makeText(this, "Rellene todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun limpiar(){
        etIdentificacion.setText("")
        etApellidos.setText("")
        etNombres.setText("")
        etDireccion.setText("")
        etTelefono.setText("")
        spinner.setFocusable( true )
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
        Toast.makeText(this, parent?.getItemAtPosition(pos).toString(), Toast.LENGTH_SHORT).show()
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.miaplicacion_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {

            R.id.listar_ninos -> {
                val intent = Intent(this, MainActivity::class.java)
                //intent.putExtra("name", etNombre.text.toString())
                startActivity(intent)
                true
            }
            R.id.localizacion_ninos -> {
                //showHelp()
                Toast.makeText(this,
                    "Seleccion?? Localizaci??n de Ni??os",
                    Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        val findItem = menu?.findItem(R.id.registrar_ninos)
        findItem?.isEnabled = false
        return true
    }
}