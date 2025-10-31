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

        val tvTMB = findViewById<TextView>(R.id.tvResultadoGasto)
        val tvGastoTotal = findViewById<TextView>(R.id.tvGastoCaloricoTotal)
        val tvMensagemGasto = findViewById<TextView>(R.id.tvMensagemGasto)
        val btnCalcularPesoIdeal = findViewById<Button>(R.id.btnCalcularPesoIdeal)
        val btnVoltar = findViewById<Button>(R.id.btnVoltarGasto)

        val usuario = if (android.os.Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_USUARIO, Usuario::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_USUARIO)
        }

        usuario?.let {
            tvMensagemGasto.text = getString(R.string.msg_gasto_personalizada, it.nome)

            val tmb = Calculos.calcularTMB(it)
            tvResultado.text = "%.2f kcal".format(tmb)
            val gastoTotal = Calculos.calcularGastoCaloricoDiario(it)

            it.tmb = tmb
            it.gastoCaloricoDiario = gastoTotal

            tvGastoTotal.text = "%.0f kcal/dia".format(gastoTotal)
            tvTMB.text = "%.0f kcal/dia".format(tmb)

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