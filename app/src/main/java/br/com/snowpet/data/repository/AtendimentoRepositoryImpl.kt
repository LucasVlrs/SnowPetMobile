package br.com.snowpet.data.repository

import androidx.sqlite.db.SimpleSQLiteQuery
import br.com.snowpet.core.database.SnowPetDatabase
import br.com.snowpet.data.local.entity.AtendimentoEntity
import br.com.snowpet.data.local.entity.BanhoETosaEntity
import br.com.snowpet.data.local.entity.ClienteEntity
import br.com.snowpet.data.local.entity.ClientePetEntity
import br.com.snowpet.domain.repository.AtendimentoRepository
import br.com.snowpet.domain.repository.ClienteRepository
import javax.inject.Inject

class AtendimentoRepositoryImpl @Inject constructor(
    private val database: SnowPetDatabase,
) : AtendimentoRepository {

    override suspend fun createNewAtendimento(atendimentoEntity: AtendimentoEntity): Long {
        try {
            return database.atendimentoDao.insert(atendimentoEntity)
        } catch (e: Exception) {
            e.printStackTrace()
            return -1
        }
    }

    override suspend fun getListAtendimentos(): List<AtendimentoEntity> {
        val query = "SELECT * FROM atendimento "
        return  database.atendimentoDao.getListAtendimentos(SimpleSQLiteQuery(query))
    }

    override suspend fun getListBanhoETosa(): List<BanhoETosaEntity> {
        val query = "SELECT * FROM banho_e_tosa "
        return  database.atendimentoDao.getListBanhoETosa(SimpleSQLiteQuery(query))
    }

}