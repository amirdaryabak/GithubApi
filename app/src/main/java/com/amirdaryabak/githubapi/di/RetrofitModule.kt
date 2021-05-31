package com.amirdaryabak.githubapi.di

import android.content.SharedPreferences
import com.amirdaryabak.data.local.sharedpreferences.PrefsUtils
import com.amirdaryabak.data.local.sharedpreferences.PrefsUtilsImpl
import com.amirdaryabak.data.webservice.MyWebservice
import com.amirdaryabak.data.webservice.WebserviceFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Singleton
    @Provides
    fun providePrefsUtils(
        sharedPreferences: SharedPreferences
    ) = PrefsUtilsImpl(sharedPreferences) as PrefsUtils

    @Provides
    fun provideGithubWebservice(webserviceFactory: WebserviceFactory): MyWebservice {
        return webserviceFactory.createGithubService(MyWebservice::class.java)
    }


}