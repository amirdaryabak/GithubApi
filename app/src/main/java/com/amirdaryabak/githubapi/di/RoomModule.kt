package com.amirdaryabak.githubapi.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import com.amirdaryabak.data.local.database.DatabaseContract
import com.amirdaryabak.data.local.database.MyDao
import com.amirdaryabak.data.local.database.MyDatabase
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RoomModule {

    @Provides
    @Singleton
    fun provideMyDatabase(@ApplicationContext context: Context): MyDatabase =
        Room.databaseBuilder(context, MyDatabase::class.java, DatabaseContract.DATABASE_NAME)
            .build()

    @Provides
    fun provideMyDao(myDatabase: MyDatabase): MyDao {
        return myDatabase.myDao()
    }

}