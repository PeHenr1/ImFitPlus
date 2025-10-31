package br.edu.ifsp.scl.ads.prdm.sc3039048.imfitplus.ui

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.prdm.sc3039048.imfitplus.R
import br.edu.ifsp.scl.ads.prdm.sc3039048.imfitplus.databinding.ActivityResultadoImcBinding
import br.edu.ifsp.scl.ads.prdm.sc3039048.imfitplus.model.Calculos
import br.edu.ifsp.scl.ads.prdm.sc3039048.imfitplus.model.Constant.EXTRA_USUARIO
import br.edu.ifsp.scl.ads.prdm.sc3039048.imfitplus.model.Usuario

class ResultadoIMCActivity : AppCompatActivity() {

    private val binding: ActivityResultadoImcBinding by lazy {
        ActivityResultadoImcBinding.inflate(layoutInflater)
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
            binding.tvNome.text = getString(R.string.ola_usuario, it.nome)

            val imc = it.imc
            val categoria = Calculos.categoriaIMC(imc)
            binding.tvIMC.text = "%.2f".format(imc)
            binding.tvCategoria.text = categoria

            usuario.categoria = categoria
            atualizarStatusUI(categoria, binding.imgStatus, binding.tvMensagemCategoria)

            binding.btnGastoCalorico.setOnClickListener { _ ->
                val intentGC = Intent(this, GastoCaloricoActivity::class.java)
                intentGC.putExtra(EXTRA_USUARIO, it)
                startActivity(intentGC)
            }
        }

        binding.btnVoltar.setOnClickListener {
            finish()
        }
    }

    private fun atualizarStatusUI(
        categoria: String,
        imgStatus: ImageView,
        tvMensagemCategoria: TextView
    ) {
        val icone: Int = when (categoria) {
            "Abaixo do peso" -> R.drawable.ic_warning
            "Normal" -> R.drawable.ic_check
            "Sobrepeso" -> R.drawable.ic_warning
            "Obesidade" -> R.drawable.ic_error
            else -> R.drawable.ic_error
        }

        val mensagem: String = when (categoria) {
            "Abaixo do peso" -> getString(R.string.msg_abaixo_peso)
            "Normal" -> getString(R.string.msg_normal)
            "Sobrepeso" -> getString(R.string.msg_sobrepeso)
            "Obesidade" -> getString(R.string.msg_obesidade)
            else -> getString(R.string.msg_erro)
        }

        imgStatus.setImageResource(icone)
        tvMensagemCategoria.text = mensagem
    }
}