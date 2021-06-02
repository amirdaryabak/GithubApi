package com.amirdaryabak.githubapi.ui.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.amirdaryabak.data.local.sharedpreferences.PrefsUtils
import com.amirdaryabak.data.utils.Status
import com.amirdaryabak.githubapi.R
import com.amirdaryabak.githubapi.databinding.FragmentSplashBinding
import com.amirdaryabak.githubapi.ui.BaseFragment
import com.amirdaryabak.githubapi.ui.MainActivity
import com.amirdaryabak.githubapi.ui.viewmodel.AddModelViewModel
import com.amirdaryabak.githubapi.util.Constants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import javax.inject.Inject


@ExperimentalCoroutinesApi
@AndroidEntryPoint
class SplashFragment : BaseFragment(R.layout.fragment_splash) {

    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AddModelViewModel by viewModels()

    @Inject
    lateinit var prefsUtils: PrefsUtils

    companion object {
        var isFirstTime = true
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (isFirstTime) {
            isFirstTime = false
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://github.com/login/oauth/authorize?client_id=${Constants.clientId}&redirect_uri=${Constants.redirectUrl}"),
            )
            startActivity(intent)
        }

        if (prefsUtils.haveCode()) {
            viewModel.getAccessToken(
                Constants.clientId,
                Constants.clientSecrets,
                prefsUtils.getCode(),
            )
            lifecycleScope.launchWhenResumed {
                viewModel.getAccessToken.collect { event ->
                    event.getContentIfNotHandled()?.let { response ->
                        when (response.status) {
                            Status.SUCCESS -> {
//                            binding.progressBar.visibility = View.GONE
                                response.data?.let { result ->
                                    prefsUtils.setToken(result.accessToken)
                                    intentToMainActivity()
                                }
                            }
                            Status.ERROR -> {
                                binding.apply {
//                                progressBar.visibility = View.GONE
//                                txtEmptyList.visibility = View.VISIBLE
//                                txtEmptyList.text = response.message
                                }
                            }
                            Status.LOADING -> {
                                binding.apply {
//                                txtEmptyList.visibility = View.GONE
//                                progressBar.visibility = View.VISIBLE
                                }
                            }
                            else -> Unit
                        }
                    }
                }
            }
        }


        binding.apply {

        }
    }

    private fun intentToMainActivity() {
        Intent(requireActivity(), MainActivity::class.java).also {
            it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(it)
            requireActivity().finish()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

