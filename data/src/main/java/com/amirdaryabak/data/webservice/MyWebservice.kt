package com.amirdaryabak.data.webservice

import com.amirdaryabak.data.entity.AccessToken
import com.amirdaryabak.data.entity.Model
import retrofit2.http.*

interface MyWebservice {

    @Headers("Accept: application/json")
    @POST("login/oauth/access_token")
    @FormUrlEncoded
    suspend fun getAccessToken(
        @Field("client_id") client_id: String,
        @Field("client_secret") client_secret: String,
        @Field("code") code: String,
    ): AccessToken
}
