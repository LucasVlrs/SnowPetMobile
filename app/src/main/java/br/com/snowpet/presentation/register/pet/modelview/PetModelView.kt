package br.com.snowpet.presentation.register.pet.modelview

import br.com.snowpet.presentation.register.cliente.modelview.ClienteModelView
import java.io.Serializable

data class PetModelView(
    var nome: String,
    var idade: Int,
    var raca: String,
    var porte: String,
    var sexo: String,
    var problemasSaude: String,
    var alergias: String,
    var informacoesAdicionais: String?,
) : Serializable

fun createPet(): PetModelView {
    return PetModelView(
        nome = "",
        idade = 0,
        raca = "",
        porte = "",
        sexo = "",
        problemasSaude = "",
        alergias = "",
        informacoesAdicionais = ""
    )
}