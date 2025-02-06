package br.com.snowpet.domain.model

import java.io.Serializable

data class BanhoETosaModel (
    val idBanhoETosa: Int = 0,
    val cliente: String,
    val pet: Int,
    val tipoBanho: String,
    val valor: Double,
    val observacoes: String,
) : Serializable