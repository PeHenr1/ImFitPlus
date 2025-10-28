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

class GastoCaloricoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gasto_calorico)

        val tvResultado = findViewById<TextView>(R.id.tvResultadoGasto)
        val btnCalcularPesoIdeal = findViewById<Button>(R.id.btnCalcularPesoIdeal)
        val btnVoltar = findViewById<Button>(R.id.btnVoltarGasto)

        val usuario = if (android.os.Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_USUARIO, Usuario::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_USUARIO)
        }

        usuario?.let {
            val tmb = Calculos.calcularTMB(it)
            tvResultado.text = "Sua TMB é: %.1f kcal".format(tmb)

            btnCalcularPesoIdeal.setOnClickListener {
                val intent = Intent(this, PesoIdealActivity::class.java)
                intent.putExtra(EXTRA_USUARIO, usuario)
                startActivity(intent)
            }
        }

        btnVoltar.setOnClickListener {
            finish()
        }
    }
}
