package br.com.snowpet.data.mapper

import br.com.snowpet.data.local.entity.BanhoETosaEntity
import br.com.snowpet.domain.model.BanhoETosaModel

fun BanhoETosaEntity.toBanhoETosaModel(): BanhoETosaModel {
    return BanhoETosaModel(
        idBanhoETosa = this.idBanhoETosa,
        cliente = this.cliente,
        pet = this.pet,
        tipoBanho = this.tipoBanho,
        valor = this.valor,
        observacoes = this.observacoes
    )
}

fun BanhoETosaModel.toBanhoETosaEntity(): BanhoETosaEntity {
    return BanhoETosaEntity(
        idBanhoETosa = this.idBanhoETosa,
        cliente = this.cliente,
        pet = this.pet,
        tipoBanho = this.tipoBanho,
        valor = this.valor,
        observacoes = this.observacoes
    )
}

fun List<BanhoETosaEntity>.toBanhoETosaModelList(): List<BanhoETosaModel> {
    return this.map { it.toBanhoETosaModel() }
}

fun List<BanhoETosaModel>.toBanhoETosaEntityList(): List<BanhoETosaEntity> {
    return this.map { it.toBanhoETosaEntity() }
}