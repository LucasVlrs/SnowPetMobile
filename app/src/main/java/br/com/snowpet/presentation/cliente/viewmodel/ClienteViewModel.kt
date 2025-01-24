package br.com.snowpet.presentation.cliente.viewmodel

import androidx.lifecycle.ViewModel
import br.com.snowpet.core.viewstate.ViewState
import br.com.snowpet.domain.model.ClienteModel
import br.com.snowpet.domain.usecase.ClienteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClienteViewModel @Inject constructor(
    private val clienteUseCase: ClienteUseCase
): ViewModel() {

    private val _listaClientes = Channel<ViewState<List<ClienteModel>>>()
    val listaClientes = _listaClientes.receiveAsFlow()

    fun getListClients() {
        CoroutineScope(Dispatchers.IO).launch {
            _listaClientes.send(ViewState.Loading)
            delay(100)
            val listPoints = clienteUseCase.getListClientes()

            if (listPoints.isEmpty()) {
                _listaClientes.send(
                    ViewState.Error(
                        "Não Foram Encontrados Clientes"
                    )
                )
            } else {
                _listaClientes.send(ViewState.Success(listPoints))
            }
        }
    }

}