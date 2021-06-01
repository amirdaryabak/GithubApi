package com.amirdaryabak.githubapi.ui

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.amirdaryabak.data.local.sharedpreferences.PrefsUtils
import com.amirdaryabak.data.local.sharedpreferences.PrefsUtilsImpl
import com.amirdaryabak.githubapi.R
import com.amirdaryabak.githubapi.databinding.ActivityLoginBinding
import com.amirdaryabak.githubapi.util.Constants
import com.amirdaryabak.githubapi.util.networkCapabilities.ConnectionLiveData
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private lateinit var navController: NavController

    private lateinit var connectionLiveData: ConnectionLiveData

    @Inject
    lateinit var prefsUtils: PrefsUtils

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

    override fun onResume() {
        super.onResume()
        intent?.let { intent ->
            intent.data?.let { uri ->
                val code = uri.getQueryParameter("code")
                prefsUtils.setCode(code ?: "")
            }

        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        navigateToTrackingFragmentIfNeeded(intent)
    }

    private fun navigateToTrackingFragmentIfNeeded(intent: Intent?) {

    }

}