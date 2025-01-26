package br.com.snowpet.presentation.register.cliente.modelview

import java.io.Serializable

data class ClienteModelView(
    var nome: String,
    var cpf: String,
    var telefone: String,
) : Serializable

fun createCliente(): ClienteModelView {
    return ClienteModelView(
        nome = "",
        cpf = "",
        telefone = "",
    )
}