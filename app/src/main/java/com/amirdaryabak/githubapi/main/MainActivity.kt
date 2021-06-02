package com.amirdaryabak.githubapi.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.amirdaryabak.githubapi.R
import com.amirdaryabak.githubapi.databinding.ActivityMainBinding
import com.amirdaryabak.githubapi.ui.navigation.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import com.amirdaryabak.githubapi.util.networkCapabilities.ConnectionLiveData

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var currentNavController: LiveData<NavController>? = null

    private lateinit var connectionLiveData: ConnectionLiveData


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            setupBottomNavigationBar()
        }

        connectionLiveData = ConnectionLiveData(this)
        connectionLiveData.observe(this) {
            setUpIsNetworkAvailableView(it)
        }

    }

    private fun setupBottomNavigationBar() {
        val navGraphIds = listOf(
            R.navigation.nav_repository,
        )

        // Setup the bottom navigation view with a list of navigation graphs
        val controller = binding.bottomNav.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.nav_host_fragment,
            intent = intent
        )

        // Whenever the selected controller changes, setup the action bar.
        controller.observe(this) { navController ->
            setupActionBarWithNavController(navController)
        }
        currentNavController = controller
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
                        this@MainActivity,
                        R.color.purple_200
                    )
                )
                txtIsNetworkAvailable.text = "اینترنت قطع است"
                isNetworkAvailable.visibility = View.VISIBLE
                txtIsNetworkAvailable.visibility = View.VISIBLE
            }
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        setupBottomNavigationBar()
    }

    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false
    }

}