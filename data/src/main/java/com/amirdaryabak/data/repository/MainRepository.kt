package com.amirdaryabak.data.repository

import com.amirdaryabak.data.entity.getrepository.Repos
import com.amirdaryabak.data.entity.getuser.User
import com.amirdaryabak.data.utils.Resource

interface MainRepository {

    suspend fun getUser(): Resource<User>

    suspend fun getRepos(owner: String): Resource<Repos>

}