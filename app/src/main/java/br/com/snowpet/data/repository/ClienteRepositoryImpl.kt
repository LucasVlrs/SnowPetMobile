package br.com.snowpet.data.repository

import androidx.sqlite.db.SimpleSQLiteQuery
import br.com.snowpet.core.database.SnowPetDatabase
import br.com.snowpet.data.local.entity.ClienteEntity
import br.com.snowpet.data.local.entity.ClientePetEntity
import br.com.snowpet.domain.repository.ClienteRepository
import javax.inject.Inject

class ClienteRepositoryImpl @Inject constructor(
    private val database: SnowPetDatabase,
) : ClienteRepository {

    override suspend fun getListClientes(): List<ClienteEntity> {
        val query = "SELECT * FROM cliente "
        return  database.clienteDao.getListClientes(SimpleSQLiteQuery(query))
    }

    override suspend fun createNewCliente(clienteEntity: ClienteEntity): Long {
        try {
            return database.clienteDao.insert(clienteEntity)
        } catch (e: Exception) {
            e.printStackTrace()
            return -1
        }
    }

    override suspend fun setDonoPet(clienteCpf: String, petInternalId: Int): Long {
        try {
            val clientePetEntity =
                ClientePetEntity(clienteCpf, petInternalId)
            return database.clientepetDao.setDonoPet(clientePetEntity)
        } catch (e: Exception) {
            e.printStackTrace()
            return -1
        }
    }
}