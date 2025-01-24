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

        return  database.petDao.getListPets(SimpleSQLiteQuery(query))
    }
}

/*
[se tiver parametro] -> val conditions = mutableListOf<Pair<String, Any?>>()
conditions.add("(i.account_id = '$accountId')" to null)
val conditionsMerged = manuallyFilledFilters(conditions)
*/
