package br.com.snowpet.data.mapper

import br.com.snowpet.data.local.entity.AtendimentoEntity
import br.com.snowpet.domain.model.AtendimentoModel
import br.com.snowpet.domain.model.BanhoETosaModel

fun AtendimentoEntity.toAtendimentoModel(banhoETosaModel: BanhoETosaModel): AtendimentoModel {
    return AtendimentoModel(
        idAtendimento = this.idAtendimento,
        banhoETosa = banhoETosaModel,
        data = this.data,
        valorTotal = this.valorTotal
    )
}

fun AtendimentoModel.toAtendimentoEntity(): AtendimentoEntity {
    return AtendimentoEntity(
        idAtendimento = this.idAtendimento,
        banhoETosaId = this.banhoETosa.idBanhoETosa,
        data = this.data,
        valorTotal = this.valorTotal
    )
}

fun List<AtendimentoEntity>.toAtendimentoModelList(banhoETosaModels: List<BanhoETosaModel>): List<AtendimentoModel> {
    return this.map { entity ->
        val banhoETosaModel = banhoETosaModels.find { it.idBanhoETosa == entity.banhoETosaId }
            ?: throw IllegalArgumentException("BanhoETosaModel não encontrado para o ID ${entity.banhoETosaId}")
        entity.toAtendimentoModel(banhoETosaModel)
    }
}

fun List<AtendimentoModel>.toAtendimentoEntityList(): List<AtendimentoEntity> {
    return this.map { it.toAtendimentoEntity() }
}