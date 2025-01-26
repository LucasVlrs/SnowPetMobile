package br.com.snowpet.domain.model

import java.io.Serializable

data class PetModel(
    val nome: String,
    val idade: Int,
    val raca: String,
    val porte: String,
    val alergias: String,
    val dono: String
) : Serializable
