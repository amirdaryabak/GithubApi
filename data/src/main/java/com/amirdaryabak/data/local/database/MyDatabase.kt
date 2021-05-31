package com.amirdaryabak.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.amirdaryabak.data.entity.Model

@Database(entities = [Model::class], version = 1)
abstract class MyDatabase : RoomDatabase() {
    abstract fun myDao(): MyDao
}