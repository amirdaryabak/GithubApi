package com.amirdaryabak.data.repository

import com.amirdaryabak.data.entity.Model
import com.amirdaryabak.data.local.database.MyDao
import com.amirdaryabak.data.local.sharedpreferences.PrefsUtils
import com.amirdaryabak.data.utils.Resource
import com.amirdaryabak.data.webservice.MyWebservice
import com.amirdaryabak.data.webservice.safeApiCall
import javax.inject.Inject

class MainRepositoryImpl
@Inject constructor(
    private val prefsUtils: PrefsUtils,
    private val myDao: MyDao,
    private val api: MyWebservice
) : MainRepository {

    override suspend fun addModel(model: Model): Resource<Model> =
        safeApiCall { api.addModel(model) }

}
