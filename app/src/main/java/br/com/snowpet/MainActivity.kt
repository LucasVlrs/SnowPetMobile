package br.com.snowpet

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import br.com.snowpet.core.navigation.HomeScreenFragment
import br.com.snowpet.core.navigation.base.navigateToDeeplink
import br.com.snowpet.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setNavigation()
    }

    private fun setNavigation() {
        val navController = findNavController(R.id.nav_host_container_app)
        val bundle = Bundle()

        navController.setGraph(R.navigation.mobile_navigation, bundle)

        navController.navigateToDeeplink(HomeScreenFragment)
    }
}
