package com.amirdaryabak.githubapi.githubrepository

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.amirdaryabak.data.entity.getrepository.Repos
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

    private lateinit var reposAdapter: ReposAdapter

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

        viewModel.getRepos("token ${prefsUtils.getToken()}", prefsUtils.getUserName())

        binding.apply {

        }

        lifecycleScope.launchWhenStarted {
            viewModel.getRepos.collect { event ->
                event.getContentIfNotHandled()?.let { response ->
                    when (response.status) {
                        Status.SUCCESS -> {
                            binding.progressBar.visibility = View.GONE
                            response.data?.let { result ->
                                setupRecyclerView(result)
                                viewModel.insertRepos(result)
                            }
                        }
                        Status.ERROR -> {
                            getReposFromDB()
                            showSnackbar() {
                                viewModel.getRepos(
                                    "token ${prefsUtils.getToken()}",
                                    prefsUtils.getUserName()
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

    private fun getReposFromDB() {
        viewModel.getAllRepos.observe(viewLifecycleOwner) {
            it?.let { repos ->
                binding.progressBar.visibility = View.GONE
                setupRecyclerView(repos)
            }
        }
    }

    private fun setupAdapter(content: List<Repos>) {
        reposAdapter = ReposAdapter(
            clickListener = { item, position ->
                Toast.makeText(requireContext(), item.name, Toast.LENGTH_SHORT).show()
            },
        )
        reposAdapter.submitList(content)
    }

    private fun setupRecyclerView(content: List<Repos>) {
        setupAdapter(content)
        with(binding.rvRepos) {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = reposAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

