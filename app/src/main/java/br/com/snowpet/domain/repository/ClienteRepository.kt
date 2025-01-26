package br.com.snowpet.domain.repository

import br.com.snowpet.data.local.entity.ClienteEntity

interface ClienteRepository {
    suspend fun getListClientes(): List<ClienteEntity>
    suspend fun createNewCliente(clienteEntity: ClienteEntity): Long
}