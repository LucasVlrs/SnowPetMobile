package br.com.snowpet.presentation.homescreen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.snowpet.R
import br.com.snowpet.core.navigation.ClienteFragment
import br.com.snowpet.core.navigation.base.navigateToDeeplink
import br.com.snowpet.databinding.FragmentHomeScreenBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeScreenFragment : Fragment(R.layout.fragment_home_screen) {
    private lateinit var binding: FragmentHomeScreenBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeScreenBinding.bind(view)

        setupClick()
    }

    private fun setupClick() {
        binding.back.setOnClickListener {
            findNavController().navigateToDeeplink(ClienteFragment)
        }
    }

}