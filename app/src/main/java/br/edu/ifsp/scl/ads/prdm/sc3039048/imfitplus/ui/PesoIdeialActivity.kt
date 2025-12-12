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
import java.time.LocalDate

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

            val freqCardMax = Calculos.calculaFrequenciaCardiacaMax(LocalDate.parse(usuario.dataNasc))
            val porcentZona = Calculos.calculaZona(freqCardMax)
            usuario.freqCard = BigDecimal(freqCardMax).setScale(0, RoundingMode.HALF_UP).toDouble()

            val msgZona = when {
                (porcentZona >= 50 && porcentZona <= 60) -> "Leve"
                (porcentZona > 60 && porcentZona <= 70) -> "Queima de Gordura"
                (porcentZona > 70 && porcentZona <= 80) -> "Aeróbica"
                (porcentZona > 80 && porcentZona <= 90) -> "Anaeróbica"
                else -> "Erro no Cálculo"
            }

            val msgFreqCard = when {
                (porcentZona >= 50 && porcentZona <= 60) -> "Frequência cardíaca deve estar entre (50% - 60%)"
                (porcentZona > 60 && porcentZona <= 70) -> "Frequência cardíaca deve estar entre (60% - 70%)"
                (porcentZona > 70 && porcentZona <= 80) -> "Frequência cardíaca deve estar entre (70% - 80%)"
                (porcentZona > 80 && porcentZona <= 90) -> "Frequência cardíaca deve estar entre (80% - 90%)"
                else -> "Cuidado!"
            }

            binding.tvMensagem.text = mensagem
            binding.tvZona.text = msgZona
            binding.tvFreqCard.text = "Sua Frequência Cardíaca Máxima é de %.0f".format(freqCardMax)
            binding.tvMensagemFreq.text = msgFreqCard
        }

        binding.btnResumoSaude.setOnClickListener {
            val intent = Intent(this, ResumoSaudeActivity::class.java)
            intent.putExtra(EXTRA_USUARIO, usuario)
            startActivity(intent)
            finish()
        }
    }
}
