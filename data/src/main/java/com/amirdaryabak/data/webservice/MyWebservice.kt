package com.amirdaryabak.data.webservice

import com.amirdaryabak.data.entity.Model
import retrofit2.http.GET

interface MyWebservice {

    @GET("")
    suspend fun addModel(model: Model): Model
}
