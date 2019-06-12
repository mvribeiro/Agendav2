package com.marcos.agendaappdroid

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteDatabase
import android.database.Cursor
import android.widget.Toast
import com.example.marcos.agendav2.MainActivity
import java.security.AccessController.getContext
import java.util.jar.Attributes

class Percistencia(context: Context) : SQLiteOpenHelper(context ,DATABASE_NAME, null, 1){

    companion object {
        val DATABASE_NAME = "Contatos.db"
        val TABLE_NAME = "contato_table"
        val COL_0 = "ID"
        val COL_1 = "NOME"
        val COL_2 = "TELEFONE"
        val COL_3 = "EMAIL"
    }

    override fun onCreate(db: SQLiteDatabase) {

        db.execSQL("CREATE TABLE IF NOT EXISTS $TABLE_NAME(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME  TEXT, TELEFONE TEXT UNIQUE, EMAIL TEXT)")

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun inserirTeste(name: String) {
        val values = ContentValues()
        values.put(COL_1, name)
        val db = this.writableDatabase
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun inserirDados(nome : String, telefone: String, email:String): String{
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COL_1, nome)
        cv.put(COL_2, telefone)
        cv.put(COL_3, email)
        val resultado : Long

        resultado = db.insert(TABLE_NAME, null,cv)
        db.close()

        if (resultado.equals(-1)) {
            return "Numero existente"
        }
        else return "Contato salvo!"

    }

    fun carregarTodosContatos() : Cursor{
        val db = this.writableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_NAME",null)
    }

    fun carregarUmContato(): Cursor? {
        val db = this.readableDatabase
//        val db : SQLiteDatabase
//        db = this.writableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_NAME", null)

//        val query : String
//        query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COL_1
//        val data : Cursor
//        data = db.rawQuery(query, null)
//        return data
    }

    fun alterContato(id:String, nome : String, telefone:String, email:String):Boolean?{
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COL_0, id)
        cv.put(COL_1, nome)
        cv.put(COL_2, telefone)
        cv.put(COL_3, email)
        db.update(TABLE_NAME, cv, "ID=?", arrayOf(id))
        return true
    }

    fun deletarContato(id:String):Int?{
        val db = this.writableDatabase
        return db.delete(TABLE_NAME,    "ID=?", arrayOf(id))
    }

}

