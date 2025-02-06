package br.com.snowpet.presentation.register.atendimento.view

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import br.com.snowpet.R
import br.com.snowpet.core.lifecycleowner.collectInLifecycle
import br.com.snowpet.core.viewstate.onError
import br.com.snowpet.core.viewstate.onLoading
import br.com.snowpet.core.viewstate.onSuccess
import br.com.snowpet.data.mapper.toPetEntity
import br.com.snowpet.databinding.FragmentRegisterAtendimentoBinding
import br.com.snowpet.domain.model.ClienteModel
import br.com.snowpet.domain.model.PetModel
import br.com.snowpet.presentation.register.atendimento.viewmodel.RegisterAtendimentoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterAtendimentoFragment : Fragment(R.layout.fragment_register_atendimento){
    private lateinit var binding: FragmentRegisterAtendimentoBinding
    private val viewModel: RegisterAtendimentoViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegisterAtendimentoBinding.bind(view)

        viewModel.getClientes()
        viewModel.getPets()

        setupObservers()
        addOnTextChangeListeners()
        setupClick()
    }

    private fun setupClick(){
        binding.back.setOnClickListener{
            findNavController().popBackStack()
        }
        binding.buttonCreateAtendimento.setOnClickListener {
            viewModel.createNewAtendimento()
            findNavController().popBackStack()
        }
    }

    private fun setupObservers() {
        lifecycleScope.launchWhenStarted {
            viewModel.isCreateButtonEnabled.collect { isEnabled ->
                binding.buttonCreateAtendimento.isEnabled = isEnabled
            }
        }

        viewLifecycleOwner.collectInLifecycle(flow = viewModel.listaClientes) { state ->
            state
                .onSuccess { list ->
                    setupDropDownCliente(list)
                }
        }

        viewLifecycleOwner.collectInLifecycle(flow = viewModel.listapets) { state ->
            state
                .onSuccess { list ->
                    setupDropDownPet(list)
                }
        }
    }

    private fun setupDropDownPet(pets : List<PetModel>) {
        val nomePets = pets.map { it.nome }
        val petAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, nomePets)
        binding.dropdownPets.setAdapter(petAdapter)

        binding.dropdownPets.setOnItemClickListener { _, _, position, _ ->
            val petSelecionado = pets[position]
            viewModel.atendimentoModelView.banhoETosa.pet = petSelecionado.petId
        }

    }

    private fun setupDropDownCliente(clientes: List<ClienteModel>) {
        val nomeClientes = clientes.map { it.nome }
        val petAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, nomeClientes)
        binding.dropdownDonos.setAdapter(petAdapter)

        binding.dropdownDonos.setOnItemClickListener { _, _, position, _ ->
            val clienteSelecionado = clientes[position]
            viewModel.atendimentoModelView.banhoETosa.cliente = clienteSelecionado.cpf
        }
    }

    private fun addOnTextChangeListeners() {
        binding.tipoBanhoTosa.addTextChangedListener { text ->
            viewModel.inputTipoBanhoTosa(text.toString())
        }

        binding.valorTotal.addTextChangedListener { text ->
            viewModel.inputValorTotal(text.toString())
        }

        binding.observacoes.addTextChangedListener { text ->
            viewModel.inputObservacoes(text.toString())
        }

    }
}