package com.amirdaryabak.githubapi.login

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.amirdaryabak.data.local.sharedpreferences.PrefsUtils
import com.amirdaryabak.data.utils.Status
import com.amirdaryabak.githubapi.R
import com.amirdaryabak.githubapi.databinding.ActivityLoginBinding
import com.amirdaryabak.githubapi.main.MainActivity
import com.amirdaryabak.githubapi.util.Constants
import com.amirdaryabak.githubapi.util.networkCapabilities.ConnectionLiveData
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private var code: String? = ""
    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()


    private lateinit var connectionLiveData: ConnectionLiveData

    @Inject
    lateinit var prefsUtils: PrefsUtils

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        connectionLiveData = ConnectionLiveData(this)
        connectionLiveData.observe(this) {
            setUpIsNetworkAvailableView(it)
        }

        if (intent.data != null) {
            intent?.let { intent ->
                intent.data?.let { uri ->
                    code = uri.getQueryParameter("code")
                    viewModel.getAccessToken(
                        Constants.clientId,
                        Constants.clientSecrets,
                        code ?: "",
                    )
                }
            }
        } else {
            if (prefsUtils.isAuthenticated()) {
                intentToMainActivity()
            } else {
                val intent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(
                        "https://github.com/login/oauth/authorize?client_id=${Constants.clientId}&redirect_uri=${Constants.redirectUrl}"
                    ),
                )
                startActivity(intent)
            }

        }



        lifecycleScope.launchWhenStarted {
            viewModel.getAccessToken.collect { event ->
                event.getContentIfNotHandled()?.let { response ->
                    when (response.status) {
                        Status.SUCCESS -> {
                            binding.progressBar.visibility = View.GONE
                            response.data?.let { result ->
                                prefsUtils.setToken(result.accessToken)
                                intentToMainActivity()
                            }
                        }
                        Status.ERROR -> {
                            showSnackbar() {
                                viewModel.getAccessToken(
                                    Constants.clientId,
                                    Constants.clientSecrets,
                                    code ?: "",
                                )
                            }
                            binding.apply {
                                progressBar.visibility = View.GONE
                            }
                        }
                        Status.LOADING -> {
                            binding.apply {
                                progressBar.visibility = View.VISIBLE
                            }
                        }
                        else -> Unit
                    }
                }
            }
        }

    }

    private fun intentToMainActivity() {
        Intent(this, MainActivity::class.java).also {
            it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(it)
            finish()
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

    private fun showSnackbar(
        text: String = "خطا در برقراری ارتباط",
        actionText: String = "تلاش مجدد",
        duration: Int = Snackbar.LENGTH_LONG,
        callFunction: () -> Unit = {}
    ) {
        Snackbar.make(
            binding.rootView, text,
            duration
        ).setAnimationMode(Snackbar.ANIMATION_MODE_SLIDE)
            .setAction(actionText) {
                callFunction.invoke()
            }.apply {
                setActionTextColor(ContextCompat.getColor(this@LoginActivity, R.color.black))
            }
            .show()
    }

}