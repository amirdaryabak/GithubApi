package com.amirdaryabak.githubapi.githubrepository

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
import com.amirdaryabak.githubapi.databinding.FragmentGithubRepositoryBinding
import com.amirdaryabak.githubapi.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
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

        viewModel.getRepos(prefsUtils.getUserName())

        binding.apply {

        }

        lifecycleScope.launchWhenStarted {
            viewModel.getRepos.collect { event ->
                event.getContentIfNotHandled()?.let { response ->
                    when (response.status) {
                        Status.SUCCESS -> {
                            binding.progressBar.visibility = View.GONE
                            response.data?.let { result ->
                                Toast.makeText(requireContext(), "yay", Toast.LENGTH_SHORT).show()
                            }
                        }
                        Status.ERROR -> {
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

