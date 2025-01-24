package br.com.snowpet.domain.model

import java.io.Serializable

data class BanhoETosaModel (
    val cliente: ClienteModel,
    val pet: PetModel,
    val data: String,
    val valor: Double
) : Serializable