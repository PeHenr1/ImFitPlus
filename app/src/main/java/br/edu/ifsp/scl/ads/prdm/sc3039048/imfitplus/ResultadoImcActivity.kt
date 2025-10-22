package br.edu.ifsp.scl.ads.prdm.sc3039048.imfitplus

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultadoIMCActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado_imc)

        val tvNome = findViewById<TextView>(R.id.tvNome)
        val tvIMC = findViewById<TextView>(R.id.tvIMC)
        val tvCategoria = findViewById<TextView>(R.id.tvCategoria)
        val btnGastoCalorico = findViewById<Button>(R.id.btnGastoCalorico)
        val btnVoltar = findViewById<Button>(R.id.btnVoltar)

        val nome = intent.getStringExtra("nome") ?: ""
        val imc = intent.getFloatExtra("imc", 0f)
        val peso = intent.getFloatExtra("peso", 0f)
        val altura = intent.getFloatExtra("altura", 0f)
        val idade = intent.getIntExtra("idade", 0)
        val sexo = intent.getStringExtra("sexo") ?: "Masculino"
        val nivelAtividade = intent.getStringExtra("nivelAtividade") ?: "Sedentário"

        tvNome.text = nome
        tvIMC.text = "IMC: %.2f".format(imc)

        val categoria = when {
            imc < 18.5 -> "Abaixo do peso"
            imc < 25 -> "Normal"
            imc < 30 -> "Sobrepeso"
            else -> "Obesidade"
        }
        tvCategoria.text = categoria

        btnGastoCalorico.setOnClickListener {
            val intentGC = Intent(this, GastoCaloricoActivity::class.java)
            intentGC.putExtra("peso", peso)
            intentGC.putExtra("altura", altura)
            intentGC.putExtra("idade", idade)
            intentGC.putExtra("sexo", sexo)
            intentGC.putExtra("nivelAtividade", nivelAtividade)
            startActivity(intentGC)
        }

        btnVoltar.setOnClickListener {
            finish()
        }
    }
}
