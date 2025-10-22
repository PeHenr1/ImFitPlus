package br.edu.ifsp.scl.ads.prdm.sc3039048.imfitplus

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class GastoCaloricoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gasto_calorico)

        val btnVoltar = findViewById<Button>(R.id.btnVoltarGasto)
        btnVoltar.setOnClickListener {
            finish()
        }
    }
}
