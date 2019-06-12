package com.marcos.agendaappdroid

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteDatabase
import android.database.Cursor

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

        db.execSQL("CREATE TABLE IF NOT EXISTS $TABLE_NAME(ID INTEGER PRIMARY KEY AUTOINCREMENT , NAME  TEXT , TELEFONE TEXT , EMAIL TEXT)")

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun inserirDados(nome : String, telefone: String, email:String): Boolean{
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COL_1, nome)
        cv.put(COL_2, telefone)
        cv.put(COL_3, email)
        val res = db.insert(TABLE_NAME, null,cv)
        return !res.equals(-1)

    }

    fun carregarTodosContatos() : Cursor{
        val db = this.writableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_NAME",null)
    }

    fun carregarUmContato(id: String): Cursor {
        val db = this.writableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_NAME WHERE ID=?", arrayOf(id), null)
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

