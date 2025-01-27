package br.com.snowpet.data.mapper

import br.com.snowpet.data.local.entity.PetEntity
import br.com.snowpet.domain.model.PetModel

fun toPetModel(petEntity: PetEntity): PetModel {
    return PetModel(
        nome = petEntity.nome,
        idade = petEntity.idade,
        raca = petEntity.raca,
        porte = petEntity.porte,
        sexo = petEntity.sexo,
        problemasSaude = petEntity.problemasSaude,
        alergias = petEntity.alergias,
        informacoesAdicionais = petEntity.informacoesAdicionais
        )
}

fun List<PetEntity>?.toListPetModel(): List<PetModel> {
    val clienteModelList = mutableListOf<PetModel>()
    this?.forEach {
        clienteModelList.add(toPetModel(it))
    }
    return clienteModelList
}

fun PetModel.toPetEntity(): PetEntity {
    return PetEntity(
        nome = this.nome,
        idade = this.idade,
        raca = this.raca,
        porte = this.porte,
        sexo = this.sexo,
        problemasSaude = this.problemasSaude,
        alergias = this.alergias,
        informacoesAdicionais = this.informacoesAdicionais,
        )
}

fun List<PetModel>?.toListPetsEntity(): List<PetEntity> {
    val petEntityList = mutableListOf<PetEntity>()
    this?.forEach {
        petEntityList.add(it.toPetEntity())
    }
    return petEntityList
}
