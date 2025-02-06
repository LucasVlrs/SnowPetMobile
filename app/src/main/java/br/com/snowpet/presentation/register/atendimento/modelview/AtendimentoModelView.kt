package br.com.snowpet.presentation.register.atendimento.modelview

import br.com.snowpet.presentation.register.cliente.modelview.ClienteModelView
import br.com.snowpet.presentation.register.cliente.modelview.createCliente
import br.com.snowpet.presentation.register.pet.modelview.PetModelView
import br.com.snowpet.presentation.register.pet.modelview.createPet
import java.io.Serializable


data class AtendimentoModelView(
    var idAtendimento: Int? = 0,
    var banhoETosa: BanhoETosaModelView,
    var data: String,
    var valorTotal: Double
) : Serializable

data class BanhoETosaModelView(
    var idBanhoETosa: Int? = 0,
    var cliente: String,
    var pet: Int,
    var tipoBanho: String,
    var valor: Double,
    var observacoes: String
) : Serializable

fun createAtendimento(): AtendimentoModelView {
    return AtendimentoModelView(
        idAtendimento = 0,
        banhoETosa = createBanhoETosa(),
        data = "",
        valorTotal = 0.0
    )
}

fun createBanhoETosa(): BanhoETosaModelView {
    return BanhoETosaModelView(
        idBanhoETosa = 0,
        cliente = "",
        pet = 0,
        tipoBanho = "",
        valor = 0.0,
        observacoes = ""
    )
}
