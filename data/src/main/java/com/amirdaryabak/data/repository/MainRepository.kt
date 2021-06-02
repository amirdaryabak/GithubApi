package com.amirdaryabak.data.repository

import androidx.lifecycle.LiveData
import com.amirdaryabak.data.entity.getrepository.Repos
import com.amirdaryabak.data.entity.getuser.User
import com.amirdaryabak.data.entity.userfollowers.UserFollowers
import com.amirdaryabak.data.utils.Resource

interface MainRepository {

    suspend fun getUser(token: String): Resource<User>

    suspend fun getRepos(token: String, owner: String): Resource<List<Repos>>

    suspend fun getFollowers(token: String): Resource<List<UserFollowers>>

    suspend fun insertUser(user: User)

    suspend fun getLastUser(): User?

    suspend fun insertRepos(repos: List<Repos>)

    fun getAllRepos(): LiveData<List<Repos>?>

    suspend fun insertUserFollowers(userFollowers: List<UserFollowers>)

    fun getAllUserFollowers(): LiveData<List<UserFollowers>?>

}