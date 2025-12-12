package br.edu.ifsp.scl.ads.prdm.sc3039048.imfitplus.model

import java.time.LocalDate
import java.time.Period

object Calculos {

    fun calcularIMC(peso: Double, altura: Double): Double {
        return peso / (altura * altura)
    }

    fun categoriaIMC(imc: Double): String {
        return when {
            imc < 18.5 -> "Abaixo do peso"
            imc < 25 -> "Normal"
            imc < 30 -> "Sobrepeso"
            else -> "Obesidade"
        }
    }

    fun calcularPesoIdeal(altura: Double): Double {
        return 22 * (altura * altura)
    }

    fun calcularTMB(usuario: Usuario): Double {
        val alturaCm = usuario.altura * 100
        return if (usuario.sexo.equals("Masculino", true)) {
            66 + (13.7 * usuario.peso) + (5 * alturaCm) - (6.8 * usuario.idade)
        } else {
            655 + (9.6 * usuario.peso) + (1.8 * alturaCm) - (4.7 * usuario.idade)
        }
    }

    fun calcularGastoCaloricoDiario(usuario: Usuario): Double {
        val tmb = calcularTMB(usuario)
        val nivelLimpo = usuario.nivelAtividade.trim().lowercase()

        val fator = when (nivelLimpo) {
            "sedentário" -> 1.2
            "leve" -> 1.375
            "moderado" -> 1.55
            "intenso" -> 1.725
            else -> 1.2
        }

        return tmb * fator
    }

    fun calculateAge(birthDate: LocalDate): Int{
        val currentDate = LocalDate.now()
        return Period.between(birthDate, currentDate).years
    }

    fun calculaFrequenciaCardiacaMax(birthDate: LocalDate): Double{
        val idade = calculateAge(birthDate)
        return (220 - idade).toDouble()
    }

    fun calculaZona(valor: Double): Double{
        val calculo = valor * 100
        return calculo/220
    }
}
