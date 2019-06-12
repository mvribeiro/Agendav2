package com.example.marcos.agendav2

import android.database.Cursor
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.marcos.agendaappdroid.Percistencia
import kotlinx.android.synthetic.main.activity_main_cadastro.*

class MainActivityCadastro : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_cadastro)

        val teste = Percistencia(this)

        buttonAdicionarContato.setOnClickListener {
            val nome = editTextNome.text.toString()
            val telefone = editTextNumero.text.toString()
            val email = editTextEmail.text.toString()

            teste.inserirTeste(nome)

            val cursor = teste.carregarUmContato()
//            data = teste.carregarUmContato()
//            val dados = ArrayList<String>()

            cursor!!.moveToFirst()

//            textView.text = ""
//            editTextNome.text = null
            while (cursor.moveToNext()) {
                textView.append(cursor.getString(cursor.getColumnIndex(Percistencia.COL_1)))
                textView.append("\n")
//                Toast.makeText(this, cursor.getString(cursor.getColumnIndex(Percistencia.COL_1)).toString(), Toast.LENGTH_SHORT).show()
            }
            cursor.close()



        }

    }
}
