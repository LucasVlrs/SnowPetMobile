package br.com.snowpet.data.mapper

import br.com.snowpet.data.local.entity.PetEntity
import br.com.snowpet.domain.model.PetModel

fun toClienteModel(petEntity: PetEntity): PetModel {
    return PetModel(
        nome = petEntity.nome,
        dono = petEntity.dono,
        raca = petEntity.raca,
        porte = petEntity.porte,
        alergias = petEntity.alergias,
        idade = petEntity.idade
        )
}

fun List<PetEntity>.toListPetModel(): List<PetModel> {
    val clienteModelList = mutableListOf<PetModel>()
    this.forEach {
        clienteModelList.add(toClienteModel(it))
    }
    return clienteModelList
}

fun toClienteEntity(petModel: PetModel): PetEntity {
    return PetEntity(
        nome = petModel.nome,
        dono = petModel.dono,
        raca = petModel.raca,
        porte = petModel.porte,
        alergias = petModel.alergias,
        idade = petModel.idade
        )
}
