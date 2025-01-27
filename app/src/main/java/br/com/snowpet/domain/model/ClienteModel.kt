package br.com.snowpet.domain.model

import java.io.Serializable

data class ClienteModel(
    val nome: String,
    val cpf: String,
    val telefone: String,
    val endereco: String,
    val email: String,
    val redesSociais: String,
    val pets: List<PetModel>? = listOf()
) : Serializable