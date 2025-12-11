package br.edu.ifsp.scl.ads.prdm.sc3039048.imfitplus.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.prdm.sc3039048.imfitplus.R

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val btnDados = findViewById<Button>(R.id.btnDadosPessoais)
        val btnHistorico = findViewById<Button>(R.id.btnHistorico)

        btnDados.setOnClickListener {
            startActivity(Intent(this, DadosPessoaisActivity::class.java))
        }

        btnHistorico.setOnClickListener {
            startActivity(Intent(this, HistoricoActivity::class.java))
        }
    }
}
