package com.amirdaryabak.githubapi.di

import com.amirdaryabak.data.webservice.MyWebservice
import com.amirdaryabak.data.webservice.WebserviceFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    fun provideGithubWebservice(webserviceFactory: WebserviceFactory): MyWebservice {
        return webserviceFactory.createGithubService(MyWebservice::class.java)
    }


}