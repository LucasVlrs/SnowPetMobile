package br.com.snowpet.data.mapper

import br.com.snowpet.data.local.entity.ClienteEntity
import br.com.snowpet.domain.model.ClienteModel

fun toClienteModel(clienteEntity: ClienteEntity): ClienteModel {
    return ClienteModel(
        nome = clienteEntity.nome,
        cpf = clienteEntity.cpf,
        telefone = clienteEntity.telefone,
        )
}

fun List<ClienteEntity>.toListClienteModel(): List<ClienteModel> {
    val clienteModelList = mutableListOf<ClienteModel>()
    this.forEach {
        clienteModelList.add(toClienteModel(it))
    }
    return clienteModelList
}

fun toClienteEntity(clienteModel: ClienteModel): ClienteEntity {
    return ClienteEntity(
        nome = clienteModel.nome,
        cpf = clienteModel.cpf,
        telefone = clienteModel.telefone,
        )
}
