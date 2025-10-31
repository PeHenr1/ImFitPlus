package br.edu.ifsp.scl.ads.prdm.sc3039048.imfitplus.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Usuario(
    val nome: String,
    val idade: Int,
    val sexo: String,
    val altura: Double,
    val peso: Double,
    val nivelAtividade: String,
    var imc: Double = 0.0,
    var tmb: Double = 0.0,
    var gastoCaloricoDiario: Double = 0.0,
    var categoria: String = "",
    var pesoIdeal: Double = 0.0
) : Parcelable

