package br.com.snowpet.presentation.register.cliente.view

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import br.com.snowpet.R
import br.com.snowpet.databinding.FragmentRegisterClienteBinding
import br.com.snowpet.domain.model.ClienteModel
import br.com.snowpet.presentation.register.cliente.viewmodel.RegisterClienteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterClienteFragment : Fragment(R.layout.fragment_register_cliente) {
    private lateinit var binding: FragmentRegisterClienteBinding
    private val viewModel: RegisterClienteViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegisterClienteBinding.bind(view)

        setupObservers()
        setupClick()
    }

    private fun setupClick(){
        binding.back.setOnClickListener{
            findNavController().popBackStack()
        }
        binding.buttonCreateCliente.setOnClickListener {
            viewModel.createNewCliente()
            findNavController().popBackStack()
        }
    }

    private fun setupObservers() {
        addOnTextChangeListeners()

        lifecycleScope.launchWhenStarted {
            viewModel.isCreateButtonEnabled.collect { isEnabled ->
                binding.buttonCreateCliente.isEnabled = isEnabled
            }
        }
    }

    private fun addOnTextChangeListeners() {
        binding.nomeCliente.addTextChangedListener { text ->
            viewModel.inputNomeCliente(text.toString())
        }
        binding.cpfCliente.addTextChangedListener { text ->
            viewModel.inputCpfCliente(text.toString())
        }
        binding.telefoneCliente.addTextChangedListener { text ->
            viewModel.inputTelefoneCliente(text.toString())
        }
    }
}