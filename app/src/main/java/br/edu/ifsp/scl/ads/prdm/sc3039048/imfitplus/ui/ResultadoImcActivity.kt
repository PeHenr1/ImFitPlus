package br.edu.ifsp.scl.ads.prdm.sc3039048.imfitplus.ui

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.prdm.sc3039048.imfitplus.R
import br.edu.ifsp.scl.ads.prdm.sc3039048.imfitplus.model.Calculos
import br.edu.ifsp.scl.ads.prdm.sc3039048.imfitplus.model.Constant.EXTRA_USUARIO
import br.edu.ifsp.scl.ads.prdm.sc3039048.imfitplus.model.Usuario

class ResultadoIMCActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado_imc)

        val tvNome = findViewById<TextView>(R.id.tvNome)
        val tvIMC = findViewById<TextView>(R.id.tvIMC)
        val tvCategoria = findViewById<TextView>(R.id.tvCategoria)
        val btnGastoCalorico = findViewById<Button>(R.id.btnGastoCalorico)
        val btnVoltar = findViewById<Button>(R.id.btnVoltar)
        val tvMensagemCategoria = findViewById<TextView>(R.id.tvMensagemCategoria)
        val imgStatus= findViewById<ImageView>(R.id.imgStatus)

        val usuario = if (android.os.Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_USUARIO, Usuario::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_USUARIO)
        }
        val imc = intent.getDoubleExtra("IMC", 0.0)

        usuario?.let {
            tvNome.text = getString(R.string.ola_usuario, it.nome)

            val imc = it.imc
            val categoria = Calculos.categoriaIMC(imc)
            tvIMC.text = "%.2f".format(imc)
            tvCategoria.text = categoria

            atualizarStatusUI(categoria, tvCategoria, imgStatus, tvMensagemCategoria)

            btnGastoCalorico.setOnClickListener { _ ->
                val intentGC = Intent(this, GastoCaloricoActivity::class.java)
                intentGC.putExtra(EXTRA_USUARIO, it)
                startActivity(intentGC)
            }
        }

        btnVoltar.setOnClickListener {
            finish()
        }
    }

    private fun atualizarStatusUI(
        categoria: String,
        tvCategoria: TextView,
        imgStatus: ImageView,
        tvMensagemCategoria: TextView
    ) {
        val (cor: Int, icone: Int, mensagem: String) = when (categoria) {
            "Abaixo do peso" -> Triple(
                Color.parseColor("#FFC107"),
                R.drawable.ic_warning,
                getString(R.string.msg_abaixo_peso)
            )
            "Normal" -> Triple(
                Color.parseColor("#4CAF50"),
                R.drawable.ic_check,
                getString(R.string.msg_normal)
            )
            "Sobrepeso" -> Triple(
                Color.parseColor("#FF9800"),
                R.drawable.ic_warning,
                getString(R.string.msg_sobrepeso)
            )
            "Obesidade" -> Triple(
                Color.parseColor("#F44336"),
                R.drawable.ic_error,
                getString(R.string.msg_obesidade)
            )
            else -> Triple(
                Color.BLACK,
                R.drawable.ic_error,
                getString(R.string.msg_erro)
            )
        }

        tvCategoria.setTextColor(cor)
        imgStatus.setImageResource(icone)
        tvMensagemCategoria.text = mensagem
    }
}