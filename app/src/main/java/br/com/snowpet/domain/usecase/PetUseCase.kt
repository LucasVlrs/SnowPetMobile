package br.com.snowpet.domain.usecase

import br.com.snowpet.data.local.entity.PetEntity
import br.com.snowpet.data.mapper.toListPetModel
import br.com.snowpet.domain.model.PetModel
import br.com.snowpet.domain.repository.PetRepository
import javax.inject.Inject

class PetUseCase @Inject constructor(
    private val petRepository: PetRepository
) {
    suspend fun getListPets(): List<PetModel> {
        return petRepository.getListPets().toListPetModel()
    }

    suspend fun createNewPet(pet: PetEntity) {
        petRepository.createNewPet(pet)
    }

    suspend fun getInternalIdPet(): Int {
        return petRepository.getInternalIdPet()
    }

}