package br.edu.ifsp.scl.ads.prdm.sc3039048.imfitplus.ui

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.prdm.sc3039048.imfitplus.databinding.ActivityDadosPessoaisBinding
import br.edu.ifsp.scl.ads.prdm.sc3039048.imfitplus.model.Calculos
import br.edu.ifsp.scl.ads.prdm.sc3039048.imfitplus.model.Constant
import br.edu.ifsp.scl.ads.prdm.sc3039048.imfitplus.model.Usuario

class DadosPessoaisActivity : AppCompatActivity() {

    private val binding: ActivityDadosPessoaisBinding by lazy {
        ActivityDadosPessoaisBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        val atividades = arrayOf("Sedentário", "Leve", "Moderado", "Intenso")
        binding.spinnerAtividade.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, atividades)

        binding.btnCalcularIMC.setOnClickListener {
            val nome = binding.edtNome.text.toString().trim()
            val idadeStr = binding.edtIdade.text.toString().trim()
            val alturaStr = binding.edtAltura.text.toString().trim()
            val pesoStr = binding.edtPeso.text.toString().trim()
            val sexoId = binding.rgSexo.checkedRadioButtonId
            val nivelAtividade = binding.spinnerAtividade.selectedItem.toString()

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
            startActivity(intent)
        }
    }
}