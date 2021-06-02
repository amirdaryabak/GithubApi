package com.amirdaryabak.data.webservice

import com.amirdaryabak.data.entity.AccessToken
import com.amirdaryabak.data.entity.getrepository.Repos
import com.amirdaryabak.data.entity.getuser.User
import retrofit2.http.*

interface MyWebservice {

    @Headers("Accept: application/json")
    @POST
    @FormUrlEncoded
    suspend fun getAccessToken(
        @Url url: String = "https://github.com/login/oauth/access_token",
        @Field("client_id") client_id: String,
        @Field("client_secret") client_secret: String,
        @Field("code") code: String,
    ): AccessToken

    @Headers("Accept: application/json")
    @GET("user")
    suspend fun getUser(): User

    @Headers("Accept: application/json")
    @GET("users/{owner}/repos")
    suspend fun getRepos(
        @Path("owner") owner: String,
    ): Repos
}
