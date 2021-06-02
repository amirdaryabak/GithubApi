package com.amirdaryabak.githubapi.userprofile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import coil.load
import com.amirdaryabak.data.entity.getuser.User
import com.amirdaryabak.data.local.sharedpreferences.PrefsUtils
import com.amirdaryabak.data.utils.Status
import com.amirdaryabak.githubapi.R
import com.amirdaryabak.githubapi.databinding.FragmentUserProfileBinding
import com.amirdaryabak.githubapi.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import javax.inject.Inject


@ExperimentalCoroutinesApi
@AndroidEntryPoint
class UserProfileFragment : BaseFragment(R.layout.fragment_user_profile) {

    private var _binding: FragmentUserProfileBinding? = null
    private val binding get() = _binding!!
    private val viewModel: UserProfileViewModel by viewModels()

    @Inject
    lateinit var prefsUtils: PrefsUtils

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getUser("token ${prefsUtils.getToken()}")

        viewModel.getLastUser().observe(viewLifecycleOwner) {
            it?.let { user ->
                binding.progressBar.visibility = View.GONE
                setUpViews(user)
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.getUser.collect { event ->
                event.getContentIfNotHandled()?.let { response ->
                    when (response.status) {
                        Status.SUCCESS -> {
                            binding.progressBar.visibility = View.GONE
                            response.data?.let { result ->
                                setUpViews(result)
                                viewModel.insertUser(result)
                                prefsUtils.setUserName(result.login)
                            }
                        }
                        Status.ERROR -> {
                            showSnackbar() {
                                viewModel.getUser("token ${prefsUtils.getToken()}")
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

    private fun setUpViews(result: User) {
        binding.apply {
            ivAvatar.load(result.avatar_url) {
                placeholder(R.drawable.ic_launcher_foreground)
            }
            txtName.text = result.name
            txtUserName.text = result.login
            txtUserBio.text = result.bio
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

