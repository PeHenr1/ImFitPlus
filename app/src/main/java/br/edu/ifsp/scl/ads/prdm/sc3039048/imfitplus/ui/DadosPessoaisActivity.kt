package br.edu.ifsp.scl.ads.prdm.sc3039048.imfitplus.ui

import android.R
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.prdm.sc3039048.imfitplus.databinding.ActivityDadosPessoaisBinding
import br.edu.ifsp.scl.ads.prdm.sc3039048.imfitplus.model.Calculos
import br.edu.ifsp.scl.ads.prdm.sc3039048.imfitplus.model.Calculos.calculateAge
import br.edu.ifsp.scl.ads.prdm.sc3039048.imfitplus.model.Constant
import br.edu.ifsp.scl.ads.prdm.sc3039048.imfitplus.model.Usuario
import java.time.LocalDate
import java.time.Period

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
            val dataNascStr = binding.edtDataNasc.text.toString().trim()
            val alturaStr = binding.edtAltura.text.toString().trim()
            val pesoStr = binding.edtPeso.text.toString().trim()
            val sexoId = binding.rgSexo.checkedRadioButtonId
            val nivelAtividade = binding.spinnerAtividade.selectedItem.toString()

            if (nome.isEmpty() || dataNascStr.isEmpty() || alturaStr.isEmpty() ||
                pesoStr.isEmpty() || sexoId == -1
            ) {
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val data = LocalDate.parse(dataNascStr)
            val idade = calculateAge(data)
            val altura = alturaStr.toDouble()
            val peso = pesoStr.toDouble()
            val sexo = findViewById<RadioButton>(sexoId).text.toString()

            val usuario = Usuario(
                nome = nome,
                idade = idade,
                dataNasc = dataNascStr,
                sexo = sexo,
                altura = altura,
                peso = peso,
                nivelAtividade = nivelAtividade
            )

            usuario.imc = "%.2f".format(
                Calculos.calcularIMC(peso, altura)
            ).toDouble()

            val intent = Intent(this, ResultadoIMCActivity::class.java)
            intent.putExtra(Constant.EXTRA_USUARIO, usuario)
            startActivity(intent)
        }
    }
}