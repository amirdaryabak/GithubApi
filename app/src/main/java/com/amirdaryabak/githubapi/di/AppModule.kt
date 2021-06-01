package com.amirdaryabak.githubapi.di

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.amirdaryabak.data.local.sharedpreferences.PrefsUtils
import com.amirdaryabak.data.local.sharedpreferences.PrefsUtilsImpl
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
    fun provideEncryptedSharedPreferences(
        @ApplicationContext context: Context
    ): SharedPreferences {
        val masterKey = MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()
        return EncryptedSharedPreferences.create(
            context,
            SHARED_PREFERENCES_NAME,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    @Singleton
    @Provides
    fun provideEventBus(): EventBus = EventBus.getDefault()

    @Provides
    fun provideLibraryRepository(mainRepositoryImpl: MainRepositoryImpl): MainRepository {
        return mainRepositoryImpl
    }

    @Singleton
    @Provides
    fun providePrefsUtils(
        sharedPreferences: SharedPreferences
    ) = PrefsUtilsImpl(sharedPreferences) as PrefsUtils

}








