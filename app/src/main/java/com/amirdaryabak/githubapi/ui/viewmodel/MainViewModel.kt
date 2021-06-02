package com.amirdaryabak.githubapi.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.amirdaryabak.data.entity.AccessToken
import com.amirdaryabak.data.entity.Model
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
class AddModelViewModel
@Inject constructor(
    private val repository: MainRepository,
) : ViewModel() {

    private val _getAccessToken =
        MutableStateFlow<Event<Resource<AccessToken>>>(Event(Resource.empty(null)))
    val getAccessToken = _getAccessToken

    fun getAccessToken(
        clientId: String,
        clientSecret: String,
        code: String,
    ) = viewModelScope.launch {
        _getAccessToken.value = Event(Resource.loading(null))
        _getAccessToken.value = Event(repository.getAccessToken(clientId, clientSecret, code))
    }

}