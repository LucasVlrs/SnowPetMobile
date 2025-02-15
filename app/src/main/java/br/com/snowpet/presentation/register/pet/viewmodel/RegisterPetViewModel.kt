package br.com.snowpet.presentation.register.pet.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.snowpet.core.viewstate.ViewState
import br.com.snowpet.data.mapper.toListClienteModel
import br.com.snowpet.data.mapper.toPetEntity
import br.com.snowpet.domain.model.ClienteModel
import br.com.snowpet.domain.model.PetModel
import br.com.snowpet.domain.usecase.ClienteUseCase
import br.com.snowpet.domain.usecase.PetUseCase
import br.com.snowpet.presentation.register.pet.modelview.PetModelView
import br.com.snowpet.presentation.register.pet.modelview.createPet
import br.com.snowpet.presentation.register.viewstate.FormInputState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterPetViewModel @Inject constructor(
    private val petUseCase: PetUseCase,
    private val clienteUseCase: ClienteUseCase
) : ViewModel() {

    val petModelView: PetModelView = createPet()

    private val _listaClientes = Channel<ViewState<List<ClienteModel>>>()
    val listaClientes = _listaClientes.receiveAsFlow()

    private val _nomePet = MutableStateFlow<FormInputState>(FormInputState.Default)
    val nomePet = _nomePet.asStateFlow()

    private val _idadePet = MutableStateFlow<FormInputState>(FormInputState.Default)
    val idadePet = _idadePet.asStateFlow()

    private val _racaPet = MutableStateFlow<FormInputState>(FormInputState.Default)
    val racaPet = _racaPet.asStateFlow()

    private val _portePet = MutableStateFlow<FormInputState>(FormInputState.Default)
    val portePet = _portePet.asStateFlow()

    private val _sexoPet = MutableStateFlow<FormInputState>(FormInputState.Default)
    val sexoPet = _sexoPet.asStateFlow()

    private val _problemasDeSaudePet = MutableStateFlow<FormInputState>(FormInputState.Default)
    val problemasDeSaudePet = _problemasDeSaudePet.asStateFlow()

    private val _alergiasPet = MutableStateFlow<FormInputState>(FormInputState.Default)
    val alergiasPet = _alergiasPet.asStateFlow()

    private val _informacoesAdicionaisPet = MutableStateFlow<FormInputState>(FormInputState.Valid)
    val informacoesAdicionaisPet = _informacoesAdicionaisPet.asStateFlow()

    private val _isCreateButtonEnabled = MutableStateFlow(false)
    val isCreateButtonEnabled = _isCreateButtonEnabled.asStateFlow()

    init {
        viewModelScope.launch {
            combine(
                nomePet,
                idadePet,
                racaPet,
                portePet,
                alergiasPet,
                sexoPet,
                problemasDeSaudePet,
                informacoesAdicionaisPet,
            ) { states ->
                states.all { it is FormInputState.Valid }
            }.collect { areAllValid ->
                _isCreateButtonEnabled.emit(areAllValid)
            }
        }
    }

    fun createNewPet() {
        CoroutineScope(Dispatchers.IO).launch {
            val pet =
                PetModel(
                    nome = petModelView.nome,
                    idade = petModelView.idade,
                    raca = petModelView.raca,
                    porte = petModelView.porte,
                    sexo = petModelView.sexo,
                    problemasSaude = petModelView.problemasSaude,
                    alergias = petModelView.alergias,
                    informacoesAdicionais = petModelView.informacoesAdicionais,
                )

            petUseCase.createNewPet(
                pet.toPetEntity()
            )
        }
    }

    fun inputNomePet(nome: String?) {
        viewModelScope.launch {
            nome?.let {
                petModelView.nome = nome
                if (it.isNotEmpty()) {
                    _nomePet.emit(FormInputState.Valid)
                } else {
                    _nomePet.emit(FormInputState.Invalid())
                }
            }
        }
    }

    fun inputIdadePet(idade: String?) {
        viewModelScope.launch {
            idade?.let {
                petModelView.idade = idade.toInt()
                if (it.isNotEmpty()) {
                    _idadePet.emit(FormInputState.Valid)
                } else {
                    _idadePet.emit(FormInputState.Invalid())
                }
            }
        }
    }

    fun inputRacaPet(raca: String?) {
        viewModelScope.launch {
            raca?.let {
                petModelView.raca = raca
                if (it.isNotEmpty()) {
                    _racaPet.emit(FormInputState.Valid)
                } else {
                    _racaPet.emit(FormInputState.Invalid())
                }

            }
        }
    }

    fun inputPortePet(porte: String?) {
        viewModelScope.launch {
            porte?.let {
                petModelView.porte = porte
                if (it.isNotEmpty()) {
                    _portePet.emit(FormInputState.Valid)
                } else {
                    _portePet.emit(FormInputState.Invalid())
                }
            }
        }
    }

    fun inputSexoPet(sexo: String?) {
        viewModelScope.launch {
            sexo?.let {
                petModelView.sexo = sexo
                if (it.isNotEmpty()) {
                    _sexoPet.emit(FormInputState.Valid)
                } else {
                    _sexoPet.emit(FormInputState.Invalid())
                }
            }
        }
    }

    fun inputProblemasDeSaudePet(problemasDeSaude: String?) {
        viewModelScope.launch {
            problemasDeSaude?.let {
                petModelView.problemasSaude = problemasDeSaude
                if (it.isNotEmpty()) {
                    _problemasDeSaudePet.emit(FormInputState.Valid)
                } else {
                    _problemasDeSaudePet.emit(FormInputState.Invalid())
                }
            }
        }
    }

    fun inputAlergiasPet(alergias: String?) {
        viewModelScope.launch {
            alergias?.let {
                petModelView.alergias = alergias
                if (it.isNotEmpty()) {
                    _alergiasPet.emit(FormInputState.Valid)
                } else {
                    _alergiasPet.emit(FormInputState.Invalid())
                }
            }
        }
    }

    fun inputInformacoesAdicionaisPet(informacoesAdicionais: String?) {
        viewModelScope.launch {
            informacoesAdicionais?.let {
                petModelView.informacoesAdicionais = informacoesAdicionais
            }
        }
    }

    fun getNomeCpfDonos() {
        CoroutineScope(Dispatchers.IO).launch {
            _listaClientes.send(ViewState.Loading)
            val listaClientes = clienteUseCase.getListClientes()

            if (listaClientes.isEmpty()) {
                _listaClientes.send(ViewState.Error("Não foram encontrados Donos"))
            } else {
                _listaClientes.send(ViewState.Success(listaClientes.toListClienteModel()))
            }
        }
    }

    fun setDonoPet(cpfDono: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val petRegistrado = petUseCase.getInternalIdPet()
            clienteUseCase.setDonoPet(cpfDono, petRegistrado)
        }
    }

}