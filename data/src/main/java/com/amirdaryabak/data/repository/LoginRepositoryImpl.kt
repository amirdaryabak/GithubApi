package com.amirdaryabak.data.repository

import com.amirdaryabak.data.entity.AccessToken
import com.amirdaryabak.data.local.database.MyDao
import com.amirdaryabak.data.local.sharedpreferences.PrefsUtils
import com.amirdaryabak.data.utils.Resource
import com.amirdaryabak.data.webservice.MyWebservice
import com.amirdaryabak.data.webservice.safeApiCall
import javax.inject.Inject

class LoginRepositoryImpl
@Inject constructor(
    private val prefsUtils: PrefsUtils,
    private val myDao: MyDao,
    private val api: MyWebservice
) : LoginRepository {

    override suspend fun getAccessToken(
        clientId: String,
        clientSecret: String,
        code: String,
    ): Resource<AccessToken> = safeApiCall {
        api.getAccessToken(
            client_id = clientId,
            client_secret = clientSecret,
            code = code
    ) }

}
