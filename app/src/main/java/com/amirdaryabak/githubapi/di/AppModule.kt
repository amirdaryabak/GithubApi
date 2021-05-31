package com.amirdaryabak.githubapi.di

import android.content.Context
import android.content.SharedPreferences
import com.amirdaryabak.data.repository.MainRepository
import com.amirdaryabak.data.repository.MainRepositoryImpl
import com.amirdaryabak.githubapi.util.Constants.SHARED_PREFERENCES_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.greenrobot.eventbus.EventBus
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)

    @Singleton
    @Provides
    fun provideEventBus(): EventBus = EventBus.getDefault()

    @Provides
    fun provideLibraryRepository(mainRepositoryImpl: MainRepositoryImpl): MainRepository {
        return mainRepositoryImpl
    }

}








