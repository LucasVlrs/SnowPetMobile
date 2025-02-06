package br.com.snowpet.presentation.register.atendimento.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.snowpet.core.viewstate.ViewState
import br.com.snowpet.data.mapper.toAtendimentoEntity
import br.com.snowpet.data.mapper.toListClienteModel
import br.com.snowpet.domain.model.AtendimentoModel
import br.com.snowpet.domain.model.BanhoETosaModel
import br.com.snowpet.domain.model.ClienteModel
import br.com.snowpet.domain.model.PetModel
import br.com.snowpet.domain.usecase.AtendimentoUseCase
import br.com.snowpet.domain.usecase.ClienteUseCase
import br.com.snowpet.domain.usecase.PetUseCase
import br.com.snowpet.presentation.register.atendimento.modelview.AtendimentoModelView
import br.com.snowpet.presentation.register.atendimento.modelview.createAtendimento
import br.com.snowpet.presentation.register.viewstate.FormInputState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterAtendimentoViewModel @Inject constructor(
    private val atendimentoUseCase: AtendimentoUseCase,
    private val petUseCase: PetUseCase,
    private val clienteUseCase: ClienteUseCase,
): ViewModel() {

    val atendimentoModelView: AtendimentoModelView = createAtendimento()

    private val _listapets = Channel<ViewState<List<PetModel>>>()
    val listapets = _listapets.receiveAsFlow()

    private val _listaClientes = Channel<ViewState<List<ClienteModel>>>()
    val listaClientes = _listaClientes.receiveAsFlow()

    private val _cpfCliente = MutableStateFlow<FormInputState>(FormInputState.Default)
    val cpfCliente = _cpfCliente.asStateFlow()

    private val _idPet = MutableStateFlow<FormInputState>(FormInputState.Default)
    val idPet = _idPet.asStateFlow()

    private val _tipoBanho = MutableStateFlow<FormInputState>(FormInputState.Default)
    val tipoBanho = _tipoBanho.asStateFlow()

    private val _valorTotal = MutableStateFlow<FormInputState>(FormInputState.Default)
    val valorTotal = _valorTotal.asStateFlow()

    private val _observacoes = MutableStateFlow<FormInputState>(FormInputState.Default)
    val observacoes = _observacoes.asStateFlow()

    val _isCreateButtonEnabled = MutableStateFlow(false)
    val isCreateButtonEnabled = _isCreateButtonEnabled.asStateFlow()

    init {
        viewModelScope.launch {
            combine(
                cpfCliente,
                idPet,
                tipoBanho,
                valorTotal
            ) { states ->
                states.all { it is FormInputState.Valid }
            }.collect { isEnabled ->
                _isCreateButtonEnabled.emit(isEnabled)
            }
        }
    }

    fun getClientes() {
        CoroutineScope(Dispatchers.IO).launch {
            _listaClientes.send(ViewState.Loading)
            delay(100)
            val listaClientes = clienteUseCase.getListClientes()
            if (listaClientes.isEmpty()) {
                _listaClientes.send(
                    ViewState.Error(
                        "Não Foram Encontrados Clientes"
                    )
                )
            } else {
                _listaClientes.send(ViewState.Success(listaClientes.toListClienteModel()))
            }
        }
    }

    fun getPets() {
        CoroutineScope(Dispatchers.IO).launch {
            _listapets.send(ViewState.Loading)
            delay(100)
            val listPoints = petUseCase.getListPets()

            if (listPoints.isEmpty()) {
                _listapets.send(
                    ViewState.Error(
                        "Não Foram Encontrados Pets"
                    )
                )
            } else {
                _listapets.send(ViewState.Success(listPoints))
            }
        }
    }

    fun createNewAtendimento() {
        CoroutineScope(Dispatchers.IO).launch {
            val atendimento =
                AtendimentoModel(
                    banhoETosa = BanhoETosaModel(
                        cliente = atendimentoModelView.banhoETosa.cliente,
                        pet = atendimentoModelView.banhoETosa.pet,
                        tipoBanho = atendimentoModelView.banhoETosa.tipoBanho,
                        valor = atendimentoModelView.banhoETosa.valor,
                        observacoes = atendimentoModelView.banhoETosa.observacoes
                    ),
                    data = atendimentoModelView.data,
                    valorTotal = atendimentoModelView.valorTotal
                )

            atendimentoUseCase.createNewAtendimento(
                atendimento.toAtendimentoEntity()
            )
        }
    }

    fun inputTipoBanhoTosa(tipoBanhoTosa: String?) {
        viewModelScope.launch {
            tipoBanhoTosa?.let {
                atendimentoModelView.banhoETosa.tipoBanho = tipoBanhoTosa
                if (it.isNotEmpty()) {
                    _tipoBanho.emit(FormInputState.Valid)
                } else {
                    _tipoBanho.emit(FormInputState.Invalid())
                }
            }
        }
    }

    fun inputValorTotal(valorTotal: String?) {
        viewModelScope.launch {
            valorTotal?.let {
                atendimentoModelView.banhoETosa.valor = valorTotal.toDoubleOrNull() ?: 0.0
                if (it.isNotEmpty()) {
                    _valorTotal.emit(FormInputState.Valid)
                } else {
                    _valorTotal.emit(FormInputState.Invalid())
                }
            }
        }
    }

    fun inputObservacoes(observacoes: String?) {
        viewModelScope.launch {
            observacoes?.let {
                atendimentoModelView.banhoETosa.observacoes = observacoes
            }
        }
    }
}