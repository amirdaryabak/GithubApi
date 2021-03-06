package com.amirdaryabak.data.webservice

import com.amirdaryabak.data.entity.AccessToken
import com.amirdaryabak.data.entity.getrepository.Repos
import com.amirdaryabak.data.entity.getuser.User
import com.amirdaryabak.data.entity.userfollowers.UserFollowers
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
    suspend fun getUser(
        @Header("Authorization") token: String,
    ): User

    @Headers("Accept: application/json")
    @GET("users/{owner}/repos")
    suspend fun getRepos(
        @Header("Authorization") token: String,
        @Path("owner") owner: String,
    ): List<Repos>

    @Headers("Accept: application/json")
    @GET("user/followers")
    suspend fun getFollowers(
        @Header("Authorization") token: String,
    ): List<UserFollowers>
}
