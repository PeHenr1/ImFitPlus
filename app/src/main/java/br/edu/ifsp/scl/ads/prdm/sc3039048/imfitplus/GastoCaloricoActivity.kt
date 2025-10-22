package br.edu.ifsp.scl.ads.prdm.sc3039048.imfitplus

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class GastoCaloricoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gasto_calorico)

        val tvResultado = findViewById<TextView>(R.id.tvResultadoGasto)
        val btnCalcularPesoIdeal = findViewById<Button>(R.id.btnCalcularPesoIdeal)
        val btnVoltar = findViewById<Button>(R.id.btnVoltarGasto)

        val sexo = intent.getStringExtra("sexo") ?: "Masculino"
        val idade = intent.getIntExtra("idade", 0)
        val peso = intent.getFloatExtra("peso", 0f)
        val altura = intent.getFloatExtra("altura", 0f)

        val tmb = if (sexo == "Masculino") {
            66 + (13.7 * peso) + (5 * altura * 100) - (6.8 * idade)
        } else {
            655 + (9.6 * peso) + (1.8 * altura * 100) - (4.7 * idade)
        }

        tvResultado.text = "Sua TMB é: %.1f kcal".format(tmb)

        btnCalcularPesoIdeal.setOnClickListener {
            val intent = Intent(this, PesoIdealActivity::class.java)
            intent.putExtra("altura", altura)
            intent.putExtra("peso", peso)
            intent.putExtra("sexo", sexo)
            startActivity(intent)
        }

        btnVoltar.setOnClickListener {
            finish()
        }
    }
}
