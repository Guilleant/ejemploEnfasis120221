package com.ingenesys.kotlin202201

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.BaseAdapter
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

//import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var listNotes: MutableList<ClsNinos>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //btnAccion.setOnClickListener { hacerAccion() }

        val fab: View = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            //Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
            //    .setAction("Action", null)
            //    .show()
            val intent = Intent(this, NinosActivity::class.java)
            startActivity(intent)
        }

        var db = DatabaseHandler( this )

        listNotes = db?.readData()

        /*
        listNotes.add(ClsNinos("TI", 1, "Pérez", "Pedro", "Sincelejo", "311"))
        listNotes.add(ClsNinos("RC", 2, "Martínez", "Ana", "Corozal", "312"))
        listNotes.add(ClsNinos("TI", 3, "Tuirán", "Rogelio", "Sincelejo", "315"))
        listNotes.add(ClsNinos("RC", 4, "Suárez", "Samira", "Sampués", "317"))
        listNotes.add(ClsNinos("TI", 5, "Velilla", "Luis", "Corozal", "301"))
        listNotes.add(ClsNinos("TI", 6, "Pérez", "Pedro", "Sincelejo", "311"))
        listNotes.add(ClsNinos("RC", 7, "Martínez", "Ana", "Corozal", "312"))
        listNotes.add(ClsNinos("TI", 8, "Tuirán", "Rogelio", "Sincelejo", "315"))
        listNotes.add(ClsNinos("RC", 9, "Suárez", "Samira", "Sampués", "317"))
        listNotes.add(ClsNinos("TI", 10, "Velilla", "Luis", "Corozal", "301"))
        */
        var notesAdapter = NinosAdapter(this, listNotes)
        lvNotes.adapter = notesAdapter
        lvNotes.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, position, id ->
            Toast.makeText(this, "Click on " + listNotes[position].apellidos, Toast.LENGTH_SHORT).show()
        }
    }

    /*
    fun hacerAccion(){
        if(etNombre.text.toString().isEmpty()){
            Toast.makeText(this,
                "El nombre no puede estar vacío",
                Toast.LENGTH_SHORT).show()
        }else{
            //Iremos a otra pantalla
            val intent = Intent(this, MainActivity2::class.java)
            intent.putExtra("name", etNombre.text.toString())
            startActivity(intent)
        }
    }
    */

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.miaplicacion_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.registrar_ninos -> {
                val intent = Intent(this, NinosActivity::class.java)
                //intent.putExtra("name", etNombre.text.toString())
                startActivity(intent)
                true
            }
            R.id.listar_ninos -> {
                //newGame()
                Toast.makeText(this, "Seleccionó Listar Niños",
                    Toast.LENGTH_SHORT).show()
                true
            }
            R.id.localizacion_ninos -> {
                //showHelp()
                Toast.makeText(this,
                    "Seleccionó Localización de Niños",
                    Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    inner class NinosAdapter : BaseAdapter {

        private var ninosList: MutableList<ClsNinos>
        private var context: Context? = null

        constructor(context: Context, ninosList: MutableList<ClsNinos>) : super() {
            this.ninosList = ninosList
            this.context = context
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {

            val view: View?
            val vh: ViewHolder

            if (convertView == null) {
                view = layoutInflater.inflate(R.layout.dato_nino, parent, false)
                vh = ViewHolder(view)
                view.tag = vh
                //Log.i("JSA", "set Tag for ViewHolder, position: " + position)
            } else {
                view = convertView
                vh = view.tag as ViewHolder
            }

            vh.tvTitle.text = ninosList[position].id.toString()
            vh.tvContent.text = ninosList[position].nombres

            return view
        }

        override fun getItem(position: Int): Any {
            return ninosList[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return ninosList.size
        }
    }

    inner class ViewHolder(view: View?) {
        val tvTitle: TextView
        val tvContent: TextView

        init {
            this.tvTitle = view?.findViewById(R.id.tvTitle) as TextView
            this.tvContent = view?.findViewById(R.id.tvContent) as TextView
        }
    }
}