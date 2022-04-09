package com.ingenesys.kotlin202201

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

//Nombre de la base de datos
val DATABASE_NAME = "bdninos"
//Nombre de la tabla
val TABLE_NAME = "tblNinos"
//Nombre de los campos de la tabla
val COL_ID = "id"
val COL_TIPOID = "tipoId"
val COL_NROID = "nroId"
val COL_APELLIDOS = "apellidos"
val COL_NOMBRES = "nombres"
val COL_DIRECCION = "direccion"
val COL_TELEFONO = "telefono"

class DatabaseHandler( var context: Context ) :SQLiteOpenHelper( context, DATABASE_NAME, null,  1 ){
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "create table " + TABLE_NAME + "( " +
                            COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            COL_TIPOID + " INTEGER DEFAULT 0, " +
                            COL_NROID + " INTEGER, " +
                            COL_APELLIDOS + " VARCHAR(100), " +
                            COL_NOMBRES + " VARCHAR(100), " +
                            COL_DIRECCION + " VARCHAR(100), " +
                            COL_TELEFONO + " VARCHAR(100) ) "

        db?.execSQL( createTable )
    }

    //solamente cuando se vaya a actualizar la base de datos
    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    fun insertData( nino: ClsNinos ): Long{
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put( COL_TIPOID, nino.tipoId )
        cv.put( COL_NROID, nino.nroId )
        cv.put( COL_APELLIDOS, nino.apellidos )
        cv.put( COL_NOMBRES, nino.nombres )
        cv.put( COL_DIRECCION, nino.direccion )
        cv.put( COL_TELEFONO, nino.telefono )

        var result = db.insert( TABLE_NAME, null, cv )

        if( result == -1.toLong() ){
            Toast.makeText( context, "¡Falló la inserción de datos!", Toast.LENGTH_SHORT ).show()
        }else{
            Toast.makeText( context, "¡Inserción exitosa!", Toast.LENGTH_SHORT ).show()
        }

        return result
    }

    fun readData(): MutableList<ClsNinos>{
        var lista: MutableList<ClsNinos> = ArrayList()

        val db = this.readableDatabase
        val consulta = "Select * from " + TABLE_NAME
        val resultado = db.rawQuery( consulta, null )

        if( resultado.moveToFirst() ){
            do {
                var nino = ClsNinos()
                nino.id = resultado.getString( 0 ).toInt()
                nino.tipoId = resultado.getString( 1 )
                nino.nroId = resultado.getString( 2 ).toInt()
                nino.apellidos = resultado.getString( 3 )
                nino.nombres = resultado.getString( 4 )
                nino.direccion = resultado.getString( 5 )
                nino.telefono = resultado.getString( 6 )
                lista.add( nino )
            }while( resultado.moveToNext() )
        }

        resultado.close()
        db.close()

        return lista
    }

}