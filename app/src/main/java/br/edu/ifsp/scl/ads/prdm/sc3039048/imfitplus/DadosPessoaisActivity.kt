package br.edu.ifsp.scl.ads.prdm.sc3039048.imfitplus

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class DadosPessoaisActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dados_pessoais)

        val edtNome = findViewById<EditText>(R.id.edtNome)
        val edtAltura = findViewById<EditText>(R.id.edtAltura)
        val edtPeso = findViewById<EditText>(R.id.edtPeso)

        val npIdade = findViewById<NumberPicker>(R.id.npIdade)
        npIdade.minValue = 1
        npIdade.maxValue = 120
        npIdade.wrapSelectorWheel = true

        val spinnerSexo = findViewById<Spinner>(R.id.spinnerSexo)

        val spinnerAtividade = findViewById<Spinner>(R.id.spinnerAtividade)
        val atividades = arrayOf("Sedentário", "Leve", "Moderado", "Intenso")
        spinnerAtividade.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, atividades)

        val btnCalcularIMC = findViewById<Button>(R.id.btnCalcularIMC)
        btnCalcularIMC.setOnClickListener {
            val nome = edtNome.text.toString().trim()
            val idade = npIdade.value
            val alturaStr = edtAltura.text.toString().trim()
            val pesoStr = edtPeso.text.toString().trim()
            val sexo = spinnerSexo.selectedItem.toString()
            val nivelAtividade = spinnerAtividade.selectedItem.toString()

            if (nome.isEmpty() || alturaStr.isEmpty() || pesoStr.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val altura = alturaStr.toFloat()
            val peso = pesoStr.toFloat()

            val imc = peso / (altura * altura)

            val intent = Intent(this, ResultadoIMCActivity::class.java)
            intent.putExtra("nome", nome)
            intent.putExtra("idade", idade)
            intent.putExtra("altura", altura)
            intent.putExtra("peso", peso)
            intent.putExtra("sexo", sexo)
            intent.putExtra("nivelAtividade", nivelAtividade)
            intent.putExtra("imc", imc)
            startActivity(intent)
        }
    }
}
