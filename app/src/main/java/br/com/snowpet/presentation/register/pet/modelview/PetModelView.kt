package br.com.snowpet.presentation.register.pet.modelview

import br.com.snowpet.presentation.register.cliente.modelview.ClienteModelView
import java.io.Serializable

data class PetModelView(
    var nome: String,
    var dono: String,
    var idade: Int,
    var raca: String,
    var porte: String,
    var alergias: String

) : Serializable

fun createPet(): PetModelView {
    return PetModelView(
        nome = "",
        dono = "",
        idade = 0,
        raca = "",
        porte = "",
        alergias = ""
    )
}