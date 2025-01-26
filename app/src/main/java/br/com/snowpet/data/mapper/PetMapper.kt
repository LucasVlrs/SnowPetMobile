package br.com.snowpet.data.mapper

import br.com.snowpet.data.local.entity.PetEntity
import br.com.snowpet.domain.model.PetModel

fun toClienteModel(petEntity: PetEntity): PetModel {
    return PetModel(
        nome = petEntity.nome,
        raca = petEntity.raca,
        porte = petEntity.porte,
        alergias = petEntity.alergias,
        idade = petEntity.idade,
        dono = petEntity.dono,
        )
}

fun List<PetEntity>.toListPetModel(): List<PetModel> {
    val clienteModelList = mutableListOf<PetModel>()
    this.forEach {
        clienteModelList.add(toClienteModel(it))
    }
    return clienteModelList
}

fun PetModel.toPetEntity(): PetEntity {
    return PetEntity(
        nome = this.nome,
        raca = this.raca,
        porte = this.porte,
        alergias = this.alergias,
        idade = this.idade,
        dono = this.dono,
        )
}
