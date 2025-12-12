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
import br.edu.ifsp.scl.ads.prdm.sc3039048.imfitplus.databinding.ActivityGastoCaloricoBinding
import java.math.BigDecimal
import java.math.RoundingMode

class GastoCaloricoActivity : AppCompatActivity() {
    private val binding: ActivityGastoCaloricoBinding by lazy {
        ActivityGastoCaloricoBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val usuario = if (android.os.Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_USUARIO, Usuario::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_USUARIO)
        }

        usuario?.let {
            binding.tvMensagemGasto.text = getString(R.string.msg_gasto_personalizada, it.nome)

            val tmb = Calculos.calcularTMB(it)
            val gastoTotal = Calculos.calcularGastoCaloricoDiario(it)

            it.tmb = BigDecimal(tmb).setScale(2, RoundingMode.HALF_UP).toDouble()
            it.gastoCaloricoDiario = BigDecimal(gastoTotal).setScale(2, RoundingMode.HALF_UP).toDouble()

            binding.tvGastoCaloricoTotal.text = "%.0f kcal/dia".format(gastoTotal)
            binding.tvResultadoGasto.text = "%.0f kcal/dia".format(tmb)

            binding.btnCalcularPesoIdeal.setOnClickListener {
                val intent = Intent(this, PesoIdealActivity::class.java)
                intent.putExtra(EXTRA_USUARIO, usuario)
                startActivity(intent)
            }
        }

        binding.btnVoltarGasto.setOnClickListener {
            finish()
        }
    }
}