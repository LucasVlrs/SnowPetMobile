package br.com.snowpet.presentation.register.cliente.modelview

import br.com.snowpet.presentation.register.pet.modelview.PetModelView
import java.io.Serializable

data class ClienteModelView(
    var nome: String,
    var cpf: String,
    var telefone: String,
    var endereco: String,
    var email: String,
    var redesSociais: String,
    var pets: List<PetModelView>
) : Serializable

fun createCliente(): ClienteModelView {
    return ClienteModelView(
        nome = "",
        cpf = "",
        telefone = "",
        endereco = "",
        email = "",
        redesSociais = "",
        pets = listOf()
    )
}