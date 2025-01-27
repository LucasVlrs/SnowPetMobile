package br.com.snowpet.domain.model

import java.io.Serializable

data class PetModel(
    val nome: String,
    val idade: Int,
    val raca: String,
    val porte: String,
    val sexo: String,
    val problemasSaude: String,
    val alergias: String,
    val informacoesAdicionais: String?,
) : Serializable