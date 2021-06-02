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

    override suspend fun getUser(token: String): Resource<User> = safeApiCall { api.getUser(token) }

    override suspend fun getRepos(token: String, owner: String): Resource<List<Repos>> =
        safeApiCall { api.getRepos(owner, token) }

    override suspend fun getFollowers(token: String): Resource<List<UserFollowers>> =
        safeApiCall { api.getFollowers(token) }

    override suspend fun insertUser(user: User) = myDao.insertUser(user)

    override suspend fun getLastUser(): User? = myDao.getLastUser()

    override suspend fun insertRepos(repos: List<Repos>) = myDao.insertRepos(repos)

    override fun getAllRepos() = myDao.getAllRepos()

    override suspend fun insertUserFollowers(userFollowers: List<UserFollowers>) =
        myDao.insertUserFollowers(userFollowers)

    override fun getAllUserFollowers() = myDao.getAllUserFollowers()

}
