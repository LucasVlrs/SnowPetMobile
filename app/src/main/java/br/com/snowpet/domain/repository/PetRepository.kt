package br.com.snowpet.domain.repository

import br.com.snowpet.data.local.entity.PetEntity

interface PetRepository {
    suspend fun getListPets(): List<PetEntity>
    suspend fun createNewPet(pet: PetEntity): Long
    suspend fun getInternalIdPet(): Int
}