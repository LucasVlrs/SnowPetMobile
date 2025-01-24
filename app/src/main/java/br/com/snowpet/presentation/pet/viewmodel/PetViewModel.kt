package br.com.snowpet.presentation.pet.viewmodel

import androidx.lifecycle.ViewModel
import br.com.snowpet.core.viewstate.ViewState
import br.com.snowpet.domain.model.PetModel
import br.com.snowpet.domain.usecase.PetUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PetViewModel @Inject constructor(
    private val petUseCase: PetUseCase
): ViewModel() {

    private val _listapets = Channel<ViewState<List<PetModel>>>()
    val listapets = _listapets.receiveAsFlow()

    fun getListPets() {
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
}