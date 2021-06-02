package com.amirdaryabak.data.repository

import com.amirdaryabak.data.entity.getrepository.Repos
import com.amirdaryabak.data.entity.getuser.User
import com.amirdaryabak.data.entity.userfollowers.UserFollowers
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

    override suspend fun getUser(): Resource<User> = safeApiCall { api.getUser() }

    override suspend fun getRepos(owner: String): Resource<List<Repos>> =
        safeApiCall { api.getRepos(owner) }

    override suspend fun getFollowers(): Resource<List<UserFollowers>> =
        safeApiCall { api.getFollowers() }

}
