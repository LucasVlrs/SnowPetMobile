package br.com.snowpet.presentation.register.cliente.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.snowpet.data.mapper.toClienteEntity
import br.com.snowpet.domain.model.ClienteModel
import br.com.snowpet.domain.usecase.ClienteUseCase
import br.com.snowpet.presentation.register.cliente.modelview.ClienteModelView
import br.com.snowpet.presentation.register.cliente.modelview.createCliente
import br.com.snowpet.presentation.register.viewstate.FormInputState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterClienteViewModel @Inject constructor(
    private val clienteUseCase: ClienteUseCase
): ViewModel() {

    val clienteModelView: ClienteModelView = createCliente()

    private val _nomeCliente = MutableStateFlow<FormInputState>(FormInputState.Default)
    val nomeCliente = _nomeCliente.asStateFlow()

    private val _cpfCliente = MutableStateFlow<FormInputState>(FormInputState.Default)
    val cpfCliente = _cpfCliente.asStateFlow()

    private val _telefoneCliente = MutableStateFlow<FormInputState>(FormInputState.Default)
    val telefoneCliente = _telefoneCliente.asStateFlow()

    private val _isCreateButtonEnabled = MutableStateFlow(false)
    val isCreateButtonEnabled = _isCreateButtonEnabled.asStateFlow()

    init {
        viewModelScope.launch {
            combine(
                nomeCliente,
                cpfCliente,
                telefoneCliente
            ) { states ->
                states.all { it is FormInputState.Valid }
            }.collect { isEnabled ->
                _isCreateButtonEnabled.emit(isEnabled)
            }
        }
    }

    fun createNewCliente() {
        CoroutineScope(Dispatchers.IO).launch {

            val cliente =
                ClienteModel(
                    clienteModelView.nome,
                    clienteModelView.cpf,
                    clienteModelView.telefone,
                )

            clienteUseCase.createNewCliente(
                cliente.toClienteEntity()
            )
        }
    }

    fun inputNomeCliente(nome: String?) {
        viewModelScope.launch {
            nome?.let {
                clienteModelView.nome = nome
                if (it.isNotEmpty()) {
                    _nomeCliente.emit(FormInputState.Valid)
                } else {
                    _nomeCliente.emit(FormInputState.Invalid())
                }
            }
        }
    }

    fun inputCpfCliente(cpf: String?) {
        viewModelScope.launch {
            cpf?.let {
                clienteModelView.cpf = cpf
                if (it.isNotEmpty()) {
                    _cpfCliente.emit(FormInputState.Valid)
                } else {
                    _cpfCliente.emit(FormInputState.Invalid())
                }
            }
        }
    }

    fun inputTelefoneCliente(telefone: String?) {
        viewModelScope.launch {
            telefone?.let {
                clienteModelView.telefone = telefone
                if (it.isNotEmpty()) {
                    _telefoneCliente.emit(FormInputState.Valid)
                } else {
                    _telefoneCliente.emit(FormInputState.Invalid())
                }
            }
        }
    }
}