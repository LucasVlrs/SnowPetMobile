package br.com.snowpet.presentation.atendimento.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.snowpet.R
import br.com.snowpet.core.lifecycleowner.collectInLifecycle
import br.com.snowpet.core.navigation.RegisterAtendimentoFragment
import br.com.snowpet.core.navigation.base.navigateToDeeplink
import br.com.snowpet.core.viewstate.onError
import br.com.snowpet.core.viewstate.onLoading
import br.com.snowpet.core.viewstate.onSuccess
import br.com.snowpet.databinding.FragmentAtendimentoBinding
import br.com.snowpet.domain.model.AtendimentoModel
import br.com.snowpet.presentation.atendimento.adapter.ListaAtendimentosAdapter
import br.com.snowpet.presentation.atendimento.viewmodel.AtendimentoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AtendimentoFragment : Fragment(R.layout.fragment_atendimento) {
    private lateinit var binding: FragmentAtendimentoBinding
    private val viewModel: AtendimentoViewModel by activityViewModels()

    private lateinit var adapter: ListaAtendimentosAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAtendimentoBinding.bind(view)
        binding.recyclerAtendimentos.layoutManager = LinearLayoutManager(requireContext())

        setupClick()
        setupObservers()
        viewModel.getListAtendimentos()
    }

    private fun setupClick() {
        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.buttonAddAtendimento.setOnClickListener{
            findNavController().navigateToDeeplink(RegisterAtendimentoFragment)
        }
    }

    private fun setupAtendimentoAdapter(list: List<AtendimentoModel>) {
        adapter = ListaAtendimentosAdapter()
        binding.recyclerAtendimentos.adapter = adapter
        adapter.submitList(list)
    }

    private fun setupObservers() {
        viewLifecycleOwner.collectInLifecycle(flow = viewModel.listaAtendimentos) { state ->
            state
                .onLoading {
                }
                .onSuccess { list ->
                    setupAtendimentoAdapter(list)
                }
                .onError { title, message ->

                }
        }
    }
}