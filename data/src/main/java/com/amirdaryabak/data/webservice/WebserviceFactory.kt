package com.amirdaryabak.data.webservice

import com.amirdaryabak.data.BuildConfig
import com.amirdaryabak.data.local.sharedpreferences.PrefsUtils
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WebserviceFactory @Inject constructor(
    prefsUtils: PrefsUtils
) {

    companion object {
        private const val BASE_URL = "https://api.github.com/"
    }

    private val headerNameAuthorization = "Authorization"
    private val headerValueAuthorization = "token ${prefsUtils.getToken()}"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(getOkhttpClient())
        .build()

    private fun getOkhttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HeadersInterceptor())
            .addInterceptor(getLoggingInterceptor())
            .build()
    }

    private fun getLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            if (BuildConfig.DEBUG) {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            } else {
                setLevel(HttpLoggingInterceptor.Level.NONE)
            }
        }
    }

    fun <S> createGithubService(serviceClass: Class<S>): S {
        return retrofit.create(serviceClass)
    }

    private inner class HeadersInterceptor : Interceptor {

        override fun intercept(chain: Interceptor.Chain): Response {

            val original = chain.request()

            val request = original.newBuilder()
                .header(
                    headerNameAuthorization,
                    headerValueAuthorization
                )
                .method(original.method, original.body)
                .build()

            val response = chain.proceed(request)

            if (response.code == 401) {
                val webserver = createGithubService(MyWebservice::class.java)
                // TODO handle unauthorized
            }

            return response

        }

    }

}