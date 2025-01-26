package br.com.snowpet.presentation.register.pet.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.snowpet.data.mapper.toPetEntity
import br.com.snowpet.domain.model.PetModel
import br.com.snowpet.domain.usecase.PetUseCase
import br.com.snowpet.presentation.register.pet.modelview.PetModelView
import br.com.snowpet.presentation.register.pet.modelview.createPet
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
class RegisterPetViewModel @Inject constructor(
    private val petUseCase: PetUseCase
): ViewModel() {

    val petModelView: PetModelView = createPet()

    private val _nomePet = MutableStateFlow<FormInputState>(FormInputState.Default)
    val nomePet = _nomePet.asStateFlow()

    private val _idadePet = MutableStateFlow<FormInputState>(FormInputState.Default)
    val idadePet = _idadePet.asStateFlow()

    private val _racaPet = MutableStateFlow<FormInputState>(FormInputState.Default)
    val racaPet = _racaPet.asStateFlow()

    private val _portePet = MutableStateFlow<FormInputState>(FormInputState.Default)
    val portePet = _portePet.asStateFlow()

    private val _alergiasPet = MutableStateFlow<FormInputState>(FormInputState.Default)
    val alergiasPet = _alergiasPet.asStateFlow()

    private val _donoPet = MutableStateFlow<FormInputState>(FormInputState.Default)
    val donoPet = _donoPet.asStateFlow()

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
                donoPet,
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
                    alergias = petModelView.alergias,
                    dono = petModelView.dono,
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

    fun inputDonoPet(dono: String?) {
        viewModelScope.launch {
            dono?.let {
                petModelView.dono = dono
                if (it.isNotEmpty()) {
                    _donoPet.emit(FormInputState.Valid)
                } else {
                    _donoPet.emit(FormInputState.Invalid())
                }
            }
        }
    }
}