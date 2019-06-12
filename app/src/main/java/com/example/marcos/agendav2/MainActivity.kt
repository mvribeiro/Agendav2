package com.example.marcos.agendav2

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.marcos.agendaappdroid.Percistencia
import kotlinx.android.synthetic.main.activity_main_cadastro.*

/**
 * Initial Commit 12/06/2019
 */

class MainActivity : AppCompatActivity() {

    lateinit var PercistenciaDB: Percistencia

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonAdicionar.setOnClickListener{
            val intent = Intent(this, MainActivityCadastro::class.java)
            startActivity(intent)
        }

        PercistenciaDB = Percistencia (this)

    }
}
