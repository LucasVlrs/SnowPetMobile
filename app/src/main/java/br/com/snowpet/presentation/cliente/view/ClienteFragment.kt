package br.com.snowpet.presentation.cliente.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.snowpet.R
import br.com.snowpet.core.lifecycleowner.collectInLifecycle
import br.com.snowpet.core.navigation.RegisterClienteFragment
import br.com.snowpet.core.navigation.base.navigateToDeeplink
import br.com.snowpet.core.viewstate.onError
import br.com.snowpet.core.viewstate.onLoading
import br.com.snowpet.core.viewstate.onSuccess
import br.com.snowpet.databinding.FragmentClienteBinding
import br.com.snowpet.domain.model.ClienteModel
import br.com.snowpet.presentation.cliente.viewmodel.ClienteViewModel
import br.com.snowpet.presentation.cliente.adapter.ListaClientesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ClienteFragment : Fragment(R.layout.fragment_cliente) {
    private lateinit var binding: FragmentClienteBinding
    private val viewModel: ClienteViewModel by activityViewModels()

    private lateinit var adapter: ListaClientesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentClienteBinding.bind(view)
        binding.recyclerClientes.layoutManager = LinearLayoutManager(requireContext())

        setupClick()
        setupObservers()
        viewModel.getListClients()
    }

    private fun setupClick() {
        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.buttonAddCliente.setOnClickListener{
            findNavController().navigateToDeeplink(RegisterClienteFragment)
        }
    }

    private fun setupClientAdapter(list: List<ClienteModel>) {
        adapter = ListaClientesAdapter()
        binding.recyclerClientes.adapter = adapter
        adapter.submitList(list)
    }

    private fun setupObservers() {
        viewLifecycleOwner.collectInLifecycle(flow = viewModel.listaClientes) { state ->
            state
                .onLoading {
                }
                .onSuccess { list ->
                    setupClientAdapter(list)
                }
                .onError { title, message ->

                }
        }
    }
}