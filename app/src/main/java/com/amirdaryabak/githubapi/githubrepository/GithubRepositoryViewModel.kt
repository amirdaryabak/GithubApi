package com.amirdaryabak.githubapi.githubrepository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amirdaryabak.data.entity.getrepository.Repos
import com.amirdaryabak.data.entity.getuser.User
import com.amirdaryabak.data.repository.MainRepository
import com.amirdaryabak.data.utils.Event
import com.amirdaryabak.data.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class GithubRepositoryViewModel
@Inject constructor(
    private val repository: MainRepository,
) : ViewModel() {

    private val _getRepos =
        MutableStateFlow<Event<Resource<List<Repos>>>>(Event(Resource.empty(null)))
    val getRepos = _getRepos

    fun getRepos(owner: String) = viewModelScope.launch {
        _getRepos.value = Event(Resource.loading(null))
        _getRepos.value = Event(repository.getRepos(owner))
    }

    fun insertRepos(repos: List<Repos>) = viewModelScope.launch {
        repository.insertRepos(repos)
    }

    val getAllRepos = repository.getAllRepos()

}