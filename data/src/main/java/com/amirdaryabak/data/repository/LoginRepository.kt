package com.amirdaryabak.data.repository

import com.amirdaryabak.data.entity.AccessToken
import com.amirdaryabak.data.entity.Model
import com.amirdaryabak.data.utils.Resource

interface LoginRepository {

    suspend fun getAccessToken(
        clientId: String,
        clientSecret: String,
        code: String,
    ): Resource<AccessToken>

}