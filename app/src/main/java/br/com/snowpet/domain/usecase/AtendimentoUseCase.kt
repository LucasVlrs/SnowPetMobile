package br.com.snowpet.domain.usecase

import br.com.snowpet.data.local.entity.AtendimentoEntity
import br.com.snowpet.data.local.entity.BanhoETosaEntity
import br.com.snowpet.data.local.entity.ClienteEntity
import br.com.snowpet.domain.repository.AtendimentoRepository
import br.com.snowpet.domain.repository.ClienteRepository
import javax.inject.Inject

class AtendimentoUseCase @Inject constructor(
    private val atendimentoRepository: AtendimentoRepository
){

    suspend fun createNewAtendimento(atendimento: AtendimentoEntity) =
        atendimentoRepository.createNewAtendimento(atendimento)

    suspend fun createNewBanhoETosa(banhoETosaEntity: BanhoETosaEntity) =
        atendimentoRepository.createNewBanhoETosa(banhoETosaEntity)

    suspend fun getListAtendimentos(): List<AtendimentoEntity> {
        return atendimentoRepository.getListAtendimentos()
    }

    suspend fun getLastBanhoETosa(): BanhoETosaEntity {
        return atendimentoRepository.getLastBanhoETosa()
    }

    suspend fun getListBanhoETosa(): List<BanhoETosaEntity> {
        return atendimentoRepository.getListBanhoETosa()
    }

}