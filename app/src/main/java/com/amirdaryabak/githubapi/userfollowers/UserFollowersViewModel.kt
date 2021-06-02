package com.amirdaryabak.githubapi.userfollowers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amirdaryabak.data.entity.getrepository.Repos
import com.amirdaryabak.data.entity.getuser.User
import com.amirdaryabak.data.entity.userfollowers.UserFollowers
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
class UserFollowersViewModel
@Inject constructor(
    private val repository: MainRepository,
) : ViewModel() {

    private val _getFollowers =
        MutableStateFlow<Event<Resource<List<UserFollowers>>>>(Event(Resource.empty(null)))
    val getFollowers = _getFollowers

    fun getFollowers() = viewModelScope.launch {
        _getFollowers.value = Event(Resource.loading(null))
        _getFollowers.value = Event(repository.getFollowers())
    }

    /*fun insertUserFollowers(userFollowers: List<UserFollowers>) = viewModelScope.launch {
        repository.insertUserFollowers(userFollowers)
    }*/

}