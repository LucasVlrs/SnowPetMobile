package br.com.snowpet.domain.model

import java.io.Serializable

data class ClienteModel(
    val nome: String,
    val cpf: String,
    val telefone: String,
) : Serializable
