package br.com.snowpet.presentation.atendimento.viewmodel

import androidx.lifecycle.ViewModel
import br.com.snowpet.core.viewstate.ViewState
import br.com.snowpet.data.mapper.toAtendimentoModelList
import br.com.snowpet.data.mapper.toBanhoETosaModelList
import br.com.snowpet.domain.model.AtendimentoModel
import br.com.snowpet.domain.usecase.AtendimentoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AtendimentoViewModel @Inject constructor(
    private val atendimentoUseCase: AtendimentoUseCase
): ViewModel(){
    private val _listaAtendimentos = Channel<ViewState<List<AtendimentoModel>>>()
    val listaAtendimentos = _listaAtendimentos.receiveAsFlow()

    fun getListAtendimentos() {
        CoroutineScope(Dispatchers.IO).launch {
            _listaAtendimentos.send(ViewState.Loading)
            delay(100)
            val listaAtendimentos = atendimentoUseCase.getListAtendimentos()
            val listaBanhoETosa = atendimentoUseCase.getListBanhoETosa()
            if (listaAtendimentos.isEmpty()) {
                _listaAtendimentos.send(
                    ViewState.Error(
                        "Não Foram Encontrados Clientes"
                    )
                )
            } else {
                _listaAtendimentos.send(
                    ViewState.Success(
                        listaAtendimentos.toAtendimentoModelList(
                            listaBanhoETosa.toBanhoETosaModelList()
                        )
                    )
                )
            }
        }
    }
}