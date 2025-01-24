package br.com.snowpet.data.repository

import android.util.Log
import androidx.sqlite.db.SimpleSQLiteQuery
import br.com.snowpet.core.database.SnowPetDatabase
import br.com.snowpet.data.local.entity.ClienteEntity
import br.com.snowpet.domain.repository.ClienteRepository
import javax.inject.Inject

class ClienteRepositoryImpl @Inject constructor(
    private val database: SnowPetDatabase,
) : ClienteRepository {
    override suspend fun getListClientes(): List<ClienteEntity> {

        val query = "SELECT * FROM cliente "

        return  database.clienteDao.getListClientes(SimpleSQLiteQuery(query))
    }
}

/*
[se tiver parametro] -> val conditions = mutableListOf<Pair<String, Any?>>()
conditions.add("(i.account_id = '$accountId')" to null)
val conditionsMerged = manuallyFilledFilters(conditions)
*/
