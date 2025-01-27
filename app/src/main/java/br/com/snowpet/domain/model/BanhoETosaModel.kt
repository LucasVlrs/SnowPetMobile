package br.com.snowpet.domain.model

import java.io.Serializable

data class BanhoETosaModel (
    val cliente: ClienteModel,
    val pet: PetModel,
    val tipoBanho: String,
    val valor: Double,
    val observacoes: String,
) : Serializable