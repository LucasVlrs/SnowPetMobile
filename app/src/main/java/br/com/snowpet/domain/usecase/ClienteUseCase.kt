package br.com.snowpet.domain.usecase

import br.com.snowpet.data.local.entity.ClienteEntity
import br.com.snowpet.domain.repository.ClienteRepository
import javax.inject.Inject

class ClienteUseCase @Inject constructor(
    private val clienteRepository: ClienteRepository
){
    suspend fun getListClientes(): List<ClienteEntity> {
        return clienteRepository.getListClientes()
    }

    suspend fun createNewCliente(clienteModel: ClienteEntity) =
        clienteRepository.createNewCliente(clienteModel)

    suspend fun setDonoPet(cpfCliente: String, internalIdPet: Int) =
        clienteRepository.setDonoPet(cpfCliente, internalIdPet)

}