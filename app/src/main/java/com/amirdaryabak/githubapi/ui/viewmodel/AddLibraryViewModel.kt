package com.amirdaryabak.githubapi.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.amirdaryabak.data.entity.Model
import com.amirdaryabak.data.repository.MainRepository
import com.amirdaryabak.data.utils.Event
import com.amirdaryabak.data.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddModelViewModel
@Inject constructor(
    private val repository: MainRepository,
) : ViewModel() {

    fun addModel(model: Model) = liveData {
        emit(Event(Resource.loading(null)))
        emit(Event(repository.addModel(model)))
    }

}