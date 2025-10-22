package br.edu.ifsp.scl.ads.prdm.sc3039048.imfitplus

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.widget.*

class DadosPessoaisActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dados_pessoais)

        val edtNome = findViewById<EditText>(R.id.edtNome)
        val edtIdade = findViewById<EditText>(R.id.edtIdade)
        val edtAltura = findViewById<EditText>(R.id.edtAltura)
        val edtPeso = findViewById<EditText>(R.id.edtPeso)
        val rgSexo = findViewById<RadioGroup>(R.id.rgSexo)
        val spinnerAtividade = findViewById<Spinner>(R.id.spinnerAtividade)
        val btnCalcularIMC = findViewById<Button>(R.id.btnCalcularIMC)

        val atividades = arrayOf("Sedentário", "Leve", "Moderado", "Intenso")
        spinnerAtividade.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, atividades)

        btnCalcularIMC.setOnClickListener {
            val nome = edtNome.text.toString().trim()
            val idadeStr = edtIdade.text.toString().trim()
            val alturaStr = edtAltura.text.toString().trim()
            val pesoStr = edtPeso.text.toString().trim()
            val sexoId = rgSexo.checkedRadioButtonId
            val nivelAtividade = spinnerAtividade.selectedItem.toString()

            if (nome.isEmpty() || idadeStr.isEmpty() || alturaStr.isEmpty() || pesoStr.isEmpty() || sexoId == -1 ) {
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val idade = idadeStr.toInt()
            val altura = alturaStr.toFloat()
            val peso = pesoStr.toFloat()
            val sexo = findViewById<RadioButton>(sexoId).text.toString()

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
