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

class ResultadoIMCActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado_imc)

        val tvNome = findViewById<TextView>(R.id.tvNome)
        val tvIMC = findViewById<TextView>(R.id.tvIMC)
        val tvCategoria = findViewById<TextView>(R.id.tvCategoria)
        val btnGastoCalorico = findViewById<Button>(R.id.btnGastoCalorico)
        val btnVoltar = findViewById<Button>(R.id.btnVoltar)

        val usuario = if (android.os.Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_USUARIO, Usuario::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_USUARIO)
        }
        val imc = intent.getDoubleExtra("IMC", 0.0)

        usuario?.let {
            tvNome.text = it.nome
            tvIMC.text = "IMC: %.2f".format(imc)
            tvCategoria.text = Calculos.categoriaIMC(imc)

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
}
