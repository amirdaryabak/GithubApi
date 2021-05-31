package com.amirdaryabak.githubapi.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.amirdaryabak.githubapi.R
import com.amirdaryabak.githubapi.databinding.ActivityLoginBinding
import com.amirdaryabak.githubapi.util.networkCapabilities.ConnectionLiveData
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private lateinit var navController: NavController

    private lateinit var connectionLiveData: ConnectionLiveData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_main) as NavHostFragment
        navController = navHostFragment.findNavController()

        val appBarConfiguration = AppBarConfiguration
            .Builder(
                R.id.splashFragment,
            )
            .build()

        setupActionBarWithNavController(navController, appBarConfiguration)

        connectionLiveData = ConnectionLiveData(this)
        connectionLiveData.observe(this) {
            setUpIsNetworkAvailableView(it)
        }

    }

    private fun setUpIsNetworkAvailableView(it: Boolean) {
        if (it) {
            binding.apply {
                isNetworkAvailable.visibility = View.GONE
                txtIsNetworkAvailable.visibility = View.GONE
            }
        } else {
            binding.apply {
                isNetworkAvailable.setBackgroundColor(
                    ContextCompat.getColor(
                        this@LoginActivity,
                        R.color.purple_200
                    )
                )
                txtIsNetworkAvailable.text = "اینترنت قطع است"
                isNetworkAvailable.visibility = View.VISIBLE
                txtIsNetworkAvailable.visibility = View.VISIBLE
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

}