package br.edu.ifsp.scl.ads.prdm.sc3039048.imfitplus

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        val btnComecar = findViewById<Button>(R.id.btnComecar)

        btnComecar.setOnClickListener {
            val intent = Intent(this, DadosPessoaisActivity::class.java)
            startActivity(intent)
        }
    }
}