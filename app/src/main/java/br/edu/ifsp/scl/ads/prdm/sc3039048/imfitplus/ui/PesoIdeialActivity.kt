package br.edu.ifsp.scl.ads.prdm.sc3039048.imfitplus.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.prdm.sc3039048.imfitplus.R
import br.edu.ifsp.scl.ads.prdm.sc3039048.imfitplus.model.Calculos
import br.edu.ifsp.scl.ads.prdm.sc3039048.imfitplus.model.Constant.EXTRA_USUARIO
import br.edu.ifsp.scl.ads.prdm.sc3039048.imfitplus.model.Usuario

class PesoIdealActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_peso_ideal)

        val tvPesoIdeal = findViewById<TextView>(R.id.tvPesoIdeal)
        val tvMensagem = findViewById<TextView>(R.id.tvMensagem)
        val btnVoltarInicio = findViewById<Button>(R.id.btnVoltarInicio)

        val usuario = intent.getParcelableExtra<Usuario>(EXTRA_USUARIO)

        usuario?.let {
            val pesoIdeal = Calculos.calcularPesoIdeal(it.altura)
            val diferenca = it.peso - pesoIdeal

            tvPesoIdeal.text = "Peso Ideal: %.2f kg".format(pesoIdeal)

            val mensagem = when {
                diferenca > 0 -> "Você está %.2f kg acima do ideal".format(diferenca)
                diferenca < 0 -> "Você está %.2f kg abaixo do ideal".format(-diferenca)
                else -> "Você está no peso ideal!"
            }

            tvMensagem.text = mensagem
        }

        btnVoltarInicio.setOnClickListener {
            val intent = Intent(this, WelcomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
            finish()
        }
    }
}
