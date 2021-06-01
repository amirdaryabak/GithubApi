package com.amirdaryabak.githubapi.ui.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.amirdaryabak.githubapi.R
import com.amirdaryabak.githubapi.databinding.FragmentSplashBinding
import com.amirdaryabak.githubapi.ui.BaseFragment
import com.amirdaryabak.githubapi.ui.MainActivity
import com.amirdaryabak.githubapi.ui.viewmodel.AddModelViewModel
import com.amirdaryabak.githubapi.util.Constants
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SplashFragment : BaseFragment(R.layout.fragment_splash) {

    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AddModelViewModel by viewModels()

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

        binding.apply {

        }
    }

    private fun intentToHomeActivity() {
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

