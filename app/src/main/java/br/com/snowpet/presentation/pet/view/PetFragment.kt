package br.com.snowpet.presentation.pet.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.snowpet.R
import br.com.snowpet.core.lifecycleowner.collectInLifecycle
import br.com.snowpet.core.navigation.RegisterPetFragment
import br.com.snowpet.core.navigation.base.navigateToDeeplink
import br.com.snowpet.core.viewstate.onError
import br.com.snowpet.core.viewstate.onLoading
import br.com.snowpet.core.viewstate.onSuccess
import br.com.snowpet.databinding.FragmentPetBinding
import br.com.snowpet.domain.model.PetModel
import br.com.snowpet.presentation.pet.adapter.ListaPetsAdapter
import br.com.snowpet.presentation.pet.viewmodel.PetViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PetFragment : Fragment(R.layout.fragment_pet) {
    private lateinit var binding: FragmentPetBinding
    private val viewModel: PetViewModel by activityViewModels()

    private lateinit var adapter: ListaPetsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPetBinding.bind(view)
        binding.recyclerPets.layoutManager = LinearLayoutManager(requireContext())

        setupClick()
        setupObservers()
        viewModel.getListPets()

    }

    private fun setupClick() {
        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.buttonAddPet.setOnClickListener{
            findNavController().navigateToDeeplink(RegisterPetFragment)
        }
    }

    private fun setupPetsAdapter(list: List<PetModel>) {
        adapter = ListaPetsAdapter()
        binding.recyclerPets.adapter = adapter
        adapter.submitList(list)
    }

    private fun setupObservers() {
        viewLifecycleOwner.collectInLifecycle(flow = viewModel.listapets) { state ->
            state
                .onLoading {
                }
                .onSuccess {
                    setupPetsAdapter(it)
                }
                .onError { title, message ->

                }
        }
    }

}