package br.com.snowpet.domain.model

import java.io.Serializable

data class PetModel(
    val nome: String,
    val dono: String,
    val idade: Int,
    val raca: String,
    val porte: String,
    val alergias: String
) : Serializable
