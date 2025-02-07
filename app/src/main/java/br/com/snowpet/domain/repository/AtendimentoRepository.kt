package br.com.snowpet.domain.repository

import br.com.snowpet.data.local.entity.AtendimentoEntity
import br.com.snowpet.data.local.entity.BanhoETosaEntity

interface AtendimentoRepository {
    suspend fun createNewAtendimento(atendimentoModel: AtendimentoEntity): Long
    suspend fun createNewBanhoETosa(banhoETosaModel: BanhoETosaEntity): Long
    suspend fun getListAtendimentos(): List<AtendimentoEntity>
    suspend fun getLastBanhoETosa(): BanhoETosaEntity
    suspend fun getListBanhoETosa(): List<BanhoETosaEntity>
}