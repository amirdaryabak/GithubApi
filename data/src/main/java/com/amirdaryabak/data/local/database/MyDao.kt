package com.amirdaryabak.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.amirdaryabak.data.entity.getrepository.Repos
import com.amirdaryabak.data.entity.getuser.User
import com.amirdaryabak.data.entity.userfollowers.UserFollowers

@Dao
interface MyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM user ORDER BY id DESC LIMIT 1")
    suspend fun getLastUser(): User?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRepos(repos: List<Repos>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserFollowers(userFollowers: List<UserFollowers>)

}