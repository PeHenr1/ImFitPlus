package br.edu.ifsp.scl.ads.prdm.sc3039048.imfitplus

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class PesoIdealActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_peso_ideal)

        val tvResultado = findViewById<TextView>(R.id.tvResultadoPesoIdeal)
        val btnVoltar = findViewById<Button>(R.id.btnVoltarPesoIdeal)

        tvResultado.text = "Aqui será calculado o seu peso ideal."

        btnVoltar.setOnClickListener {
            finish()
        }
    }
}
