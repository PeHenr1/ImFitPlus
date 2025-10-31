package br.edu.ifsp.scl.ads.prdm.sc3039048.imfitplus.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.prdm.sc3039048.imfitplus.databinding.ActivityResumoSaudeBinding
import br.edu.ifsp.scl.ads.prdm.sc3039048.imfitplus.model.Constant.EXTRA_USUARIO
import br.edu.ifsp.scl.ads.prdm.sc3039048.imfitplus.model.Usuario

class ResumoSaudeActivity : AppCompatActivity() {

    private val binding: ActivityResumoSaudeBinding by lazy {
        ActivityResumoSaudeBinding.inflate(layoutInflater)
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
            binding.tvNomeResumo.text = it.nome
            binding.tvIMCResumo.text = "%.2f".format(it.imc)
            binding.tvCategoriaResumo.text = it.categoria
            binding.tvPesoIdealResumo.text = "%.2f kg".format(it.pesoIdeal)
            binding.tvGastoCaloricoTotalResumo.text = "%.0f kcal/dia".format(it.gastoCaloricoDiario)
            calculaRecomendacaoAgua(it.peso)
        }
        binding.btnVoltarInicio.setOnClickListener {
            val intent = Intent(this, WelcomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
            finish()
        }
    }

    private fun calculaRecomendacaoAgua(
        peso: Double
    ) {
        binding.tvRecomendacaoConsumoResumo.text = "$.1f L".format(peso * 0.350)
    }
}