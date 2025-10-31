package br.edu.ifsp.scl.ads.prdm.sc3039048.imfitplus.ui

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.prdm.sc3039048.imfitplus.R
import br.edu.ifsp.scl.ads.prdm.sc3039048.imfitplus.model.Calculos
import br.edu.ifsp.scl.ads.prdm.sc3039048.imfitplus.model.Constant
import br.edu.ifsp.scl.ads.prdm.sc3039048.imfitplus.model.Usuario

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

            if (nome.isEmpty() || idadeStr.isEmpty() || alturaStr.isEmpty() || pesoStr.isEmpty() || sexoId == -1) {
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val idade = idadeStr.toInt()
            val altura = alturaStr.toFloat()
            val peso = pesoStr.toFloat()
            val sexo = findViewById<RadioButton>(sexoId).text.toString()

            val usuario =
                Usuario(nome, idade, sexo, altura.toDouble(), peso.toDouble(), nivelAtividade)
            val imc = Calculos.calcularIMC(usuario.peso, usuario.altura)

            usuario.imc = imc

            val intent = Intent(this, ResultadoIMCActivity::class.java)
            intent.putExtra(Constant.EXTRA_USUARIO, usuario)
            intent.putExtra("IMC", imc)
            startActivity(intent)
        }
    }
}