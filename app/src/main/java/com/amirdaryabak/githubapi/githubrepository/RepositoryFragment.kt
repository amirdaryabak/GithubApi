package com.amirdaryabak.githubapi.githubrepository

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.amirdaryabak.data.local.sharedpreferences.PrefsUtils
import com.amirdaryabak.githubapi.R
import com.amirdaryabak.githubapi.databinding.FragmentGithubRepositoryBinding
import com.amirdaryabak.githubapi.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject


@ExperimentalCoroutinesApi
@AndroidEntryPoint
class RepositoryFragment : BaseFragment(R.layout.fragment_github_repository) {

    private var _binding: FragmentGithubRepositoryBinding? = null
    private val binding get() = _binding!!
    private val viewModel: GithubRepositoryViewModel by viewModels()

    @Inject
    lateinit var prefsUtils: PrefsUtils

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGithubRepositoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

