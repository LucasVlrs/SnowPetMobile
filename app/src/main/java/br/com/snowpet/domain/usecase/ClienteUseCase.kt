package br.com.snowpet.domain.usecase

import br.com.snowpet.data.mapper.toListClienteModel
import br.com.snowpet.domain.model.ClienteModel
import br.com.snowpet.domain.repository.ClienteRepository
import javax.inject.Inject

class ClienteUseCase @Inject constructor(
    private val clienteRepository: ClienteRepository
){
    suspend fun getListClientes(): List<ClienteModel> {
        return clienteRepository.getListClientes().toListClienteModel()
    }
}