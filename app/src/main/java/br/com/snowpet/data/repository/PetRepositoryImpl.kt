package br.com.snowpet.data.repository

import androidx.sqlite.db.SimpleSQLiteQuery
import br.com.snowpet.core.database.SnowPetDatabase
import br.com.snowpet.data.local.entity.PetEntity
import br.com.snowpet.domain.repository.PetRepository
import javax.inject.Inject

class PetRepositoryImpl @Inject constructor(
    private val database: SnowPetDatabase,
) : PetRepository {
    override suspend fun getListPets(): List<PetEntity> {

        val query = "SELECT * FROM pet "

        return database.petDao.getListPets(SimpleSQLiteQuery(query))
    }

    override suspend fun createNewPet(pet: PetEntity): Long {
        try {
            return database.petDao.insert(pet)
        } catch (e: Exception) {
            e.printStackTrace()
            return -1
        }
    }

    override suspend fun getInternalIdPet(): Int {
        val query = "SELECT * FROM pet ORDER BY internal_id DESC LIMIT 1"
        val result = database.petDao.getListPets(SimpleSQLiteQuery(query))
        return result.firstOrNull()?.internalId ?: 0
    }

}