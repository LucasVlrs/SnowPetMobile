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

    private val _enderecoCliente = MutableStateFlow<FormInputState>(FormInputState.Default)
    val enderecoCliente = _enderecoCliente.asStateFlow()

    private val _emailCliente = MutableStateFlow<FormInputState>(FormInputState.Default)
    val emailCliente = _emailCliente.asStateFlow()

    private val _redesSociaisCliente = MutableStateFlow<FormInputState>(FormInputState.Default)
    val redesSociaisCliente = _redesSociaisCliente.asStateFlow()

    private val _isCreateButtonEnabled = MutableStateFlow(false)
    val isCreateButtonEnabled = _isCreateButtonEnabled.asStateFlow()

    init {
        viewModelScope.launch {
            combine(
                nomeCliente,
                cpfCliente,
                telefoneCliente,
                enderecoCliente,
                emailCliente,
                redesSociaisCliente
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
                    clienteModelView.endereco,
                    clienteModelView.email,
                    clienteModelView.redesSociais,
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

    fun inputEnderecoCliente(endereco: String?) {
        viewModelScope.launch {
            endereco?.let {
                clienteModelView.endereco = endereco
                if (it.isNotEmpty()) {
                    _enderecoCliente.emit(FormInputState.Valid)
                } else {
                    _enderecoCliente.emit(FormInputState.Invalid())
                }
            }
        }
    }

    fun inputEmailCliente(email: String?) {
        viewModelScope.launch {
            email?.let {
                clienteModelView.email = email
                if (it.isNotEmpty()) {
                    _emailCliente.emit(FormInputState.Valid)
                } else {
                    _emailCliente.emit(FormInputState.Invalid())
                }
            }
        }
    }

    fun inputRedesSociaisCliente(redesSociais: String?) {
        viewModelScope.launch {
            redesSociais?.let {
                clienteModelView.redesSociais = redesSociais
                if (it.isNotEmpty()) {
                    _redesSociaisCliente.emit(FormInputState.Valid)
                } else {
                    _redesSociaisCliente.emit(FormInputState.Invalid())
                }
            }
        }
    }
}