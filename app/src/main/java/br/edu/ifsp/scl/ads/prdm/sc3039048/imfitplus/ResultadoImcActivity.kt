package br.edu.ifsp.scl.ads.prdm.sc3039048.imfitplus

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView

class ResultadoIMCActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado_imc)

        val tvResultado = findViewById<TextView>(R.id.tvResultado)

        val nome = intent.getStringExtra("nome")
        val imc = intent.getFloatExtra("imc", 0f)

        tvResultado.text = "$nome, seu IMC é %.2f".format(imc)
    }
}
