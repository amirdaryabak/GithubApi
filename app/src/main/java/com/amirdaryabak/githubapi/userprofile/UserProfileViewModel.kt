package com.amirdaryabak.githubapi.userprofile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
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
class UserProfileViewModel
@Inject constructor(
    private val repository: MainRepository,
) : ViewModel() {

    private val _getUser =
        MutableStateFlow<Event<Resource<User>>>(Event(Resource.empty(null)))
    val getUser = _getUser

    private val _getLastUser =
        MutableStateFlow<User?>(null)
    val getLastUser = _getLastUser

    fun getUser() = viewModelScope.launch {
        _getUser.value = Event(Resource.loading(null))
        _getUser.value = Event(repository.getUser())
    }

    fun insertUser(user: User) = viewModelScope.launch {
        repository.insertUser(user)
    }

    fun getLastUser() = liveData {
        emit(repository.getLastUser())
    }

}