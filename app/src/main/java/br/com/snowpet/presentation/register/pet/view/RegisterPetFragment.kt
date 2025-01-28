package br.com.snowpet.presentation.register.pet.view

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import br.com.snowpet.R
import br.com.snowpet.core.lifecycleowner.collectInLifecycle
import br.com.snowpet.core.viewstate.onSuccess
import br.com.snowpet.databinding.FragmentRegisterPetBinding
import br.com.snowpet.domain.model.ClienteModel
import br.com.snowpet.presentation.register.pet.viewmodel.RegisterPetViewModel

class RegisterPetFragment : Fragment(R.layout.fragment_register_pet) {
    private lateinit var binding: FragmentRegisterPetBinding
    private val viewModel: RegisterPetViewModel by activityViewModels()

    private lateinit var dialog: DonoPetDialog

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegisterPetBinding.bind(view)

        viewModel.getNomeCpfDonos()
        dialog = DonoPetDialog()

        setupObservers()
        setupClick()
    }

    private fun setupClick(){
        binding.back.setOnClickListener{
            findNavController().popBackStack()
        }
        binding.buttonCreatePet.setOnClickListener {
            viewModel.createNewPet()
            dialog.show(childFragmentManager, "DonoPetDialog")
        }
    }

    private fun setupObservers() {
        addOnTextChangeListeners()

        lifecycleScope.launchWhenStarted {
            viewModel.isCreateButtonEnabled.collect { isEnabled ->
                binding.buttonCreatePet.isEnabled = isEnabled
            }
        }

        viewLifecycleOwner.collectInLifecycle(flow = viewModel.listaClientes) { state ->
            state.onSuccess {
                setupListDialog(it)
            }
        }
    }

    private fun setupListDialog(list: List<ClienteModel>) {
        dialog = DonoPetDialog(list) { selectedOwner ->
            viewModel.setDonoPet(selectedOwner.cpf)
        }
    }

    private fun addOnTextChangeListeners() {
        binding.nomePet.addTextChangedListener { text ->
            viewModel.inputNomePet(text.toString())
        }
        binding.racaPet.addTextChangedListener { text ->
            viewModel.inputRacaPet(text.toString())
        }
        binding.idadePet.addTextChangedListener { text ->
            viewModel.inputIdadePet(text.toString())
        }
        binding.portePet.addTextChangedListener { text ->
            viewModel.inputPortePet(text.toString())
        }
        binding.sexoPet.addTextChangedListener { text ->
            viewModel.inputSexoPet(text.toString())
        }
        binding.problemasDeSaudePet.addTextChangedListener { text ->
            viewModel.inputProblemasDeSaudePet(text.toString())
        }
        binding.alergiasPet.addTextChangedListener { text ->
            viewModel.inputAlergiasPet(text.toString())
        }
        binding.informacoesAdicionais.addTextChangedListener { text ->
            viewModel.inputInformacoesAdicionaisPet(text.toString())
        }
    }
}
