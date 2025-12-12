package br.edu.ifsp.scl.ads.prdm.sc3039048.imfitplus.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.prdm.sc3039048.imfitplus.model.Calculos
import br.edu.ifsp.scl.ads.prdm.sc3039048.imfitplus.model.Constant.EXTRA_USUARIO
import br.edu.ifsp.scl.ads.prdm.sc3039048.imfitplus.model.Usuario
import br.edu.ifsp.scl.ads.prdm.sc3039048.imfitplus.databinding.ActivityPesoIdealBinding
import java.math.BigDecimal
import java.math.RoundingMode

class PesoIdealActivity : AppCompatActivity() {

    private val binding: ActivityPesoIdealBinding by lazy {
        ActivityPesoIdealBinding.inflate(layoutInflater)
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
            val pesoIdeal = Calculos.calcularPesoIdeal(it.altura)
            val diferenca = it.peso - pesoIdeal

            binding.tvPesoIdeal.text = "%.2f kg".format(pesoIdeal)
            usuario.pesoIdeal = BigDecimal(pesoIdeal).setScale(2, RoundingMode.HALF_UP).toDouble()

            val mensagem = when {
                diferenca > 0 -> "Você está %.2f kg acima do ideal".format(diferenca)
                diferenca < 0 -> "Você está %.2f kg abaixo do ideal".format(-diferenca)
                else -> "Você está no peso ideal!"
            }

            binding.tvMensagem.text = mensagem
        }

        binding.btnResumoSaude.setOnClickListener {
            val intent = Intent(this, ResumoSaudeActivity::class.java)
            intent.putExtra(EXTRA_USUARIO, usuario)
            startActivity(intent)
            finish()
        }
    }
}
