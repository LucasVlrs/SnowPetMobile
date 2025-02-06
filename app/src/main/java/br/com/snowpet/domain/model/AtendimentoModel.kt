package br.com.snowpet.domain.model

import java.io.Serializable

data class AtendimentoModel(
    val idAtendimento: Int = 0,
    val banhoETosa: BanhoETosaModel,
    val data: String,
    val valorTotal: Double
): Serializable