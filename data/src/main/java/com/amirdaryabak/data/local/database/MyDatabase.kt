package com.amirdaryabak.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.amirdaryabak.data.entity.Model
import com.amirdaryabak.data.entity.getrepository.Repos
import com.amirdaryabak.data.entity.getuser.User
import com.amirdaryabak.data.entity.userfollowers.UserFollowers

@Database(
    entities = [
        Model::class,
        User::class,
        Repos::class,
        UserFollowers::class,
    ], version = 1
)
abstract class MyDatabase : RoomDatabase() {
    abstract fun myDao(): MyDao
}