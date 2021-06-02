package com.amirdaryabak.githubapi.userfollowers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.amirdaryabak.data.entity.userfollowers.UserFollowers
import com.amirdaryabak.data.local.sharedpreferences.PrefsUtils
import com.amirdaryabak.data.utils.Status
import com.amirdaryabak.githubapi.R
import com.amirdaryabak.githubapi.databinding.FragmentUserFollowersBinding
import com.amirdaryabak.githubapi.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import javax.inject.Inject


@ExperimentalCoroutinesApi
@AndroidEntryPoint
class UserFollowersFragment : BaseFragment(R.layout.fragment_user_followers) {

    private var _binding: FragmentUserFollowersBinding? = null
    private val binding get() = _binding!!
    private val viewModel: UserFollowersViewModel by viewModels()

    private lateinit var followersAdapter: FollowersAdapter

    @Inject
    lateinit var prefsUtils: PrefsUtils

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserFollowersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getFollowers("token ${prefsUtils.getToken()}")

        lifecycleScope.launchWhenStarted {
            viewModel.getFollowers.collect { event ->
                event.getContentIfNotHandled()?.let { response ->
                    when (response.status) {
                        Status.SUCCESS -> {
                            binding.progressBar.visibility = View.GONE
                            response.data?.let { result ->
                                setupRecyclerView(result)
                                viewModel.insertUserFollowers(result)
                            }
                        }
                        Status.ERROR -> {
                            getUserFollowersFromDB()
                            showSnackbar() {
                                viewModel.getFollowers("token ${prefsUtils.getToken()}")
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

    private fun getUserFollowersFromDB() {
        viewModel.getAllUserFollowers.observe(viewLifecycleOwner) {
            it?.let { userFollowers ->
                binding.progressBar.visibility = View.GONE
                setupRecyclerView(userFollowers)
            }
        }
    }

    private fun setupAdapter(content: List<UserFollowers>) {
        followersAdapter = FollowersAdapter(
            clickListener = { item, position ->
                Toast.makeText(requireContext(), item.login, Toast.LENGTH_SHORT).show()
            },
        )
        followersAdapter.submitList(content)
    }

    private fun setupRecyclerView(content: List<UserFollowers>) {
        setupAdapter(content)
        with(binding.rvFollowers) {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = followersAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

