package com.amirdaryabak.data.repository

import com.amirdaryabak.data.entity.Model
import com.amirdaryabak.data.utils.Resource

interface MainRepository {

    suspend fun addModel(model: Model): Resource<Model>

}