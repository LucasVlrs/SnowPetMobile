package br.com.snowpet.domain.model

import java.io.Serializable

data class AtendimentoModel(
    val cliente: ClienteModel,
    val banhoETosa: BanhoETosaModel?,
    val data: String,
    val hora: Int,
    val valorTotal: Double
): Serializable