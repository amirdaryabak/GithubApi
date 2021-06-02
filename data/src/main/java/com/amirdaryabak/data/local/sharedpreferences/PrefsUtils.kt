package com.amirdaryabak.data.local.sharedpreferences


interface PrefsUtils {

    fun isAuthenticated(): Boolean

    fun getToken(): String
    fun setToken(string: String)

    fun getUserName(): String
    fun setUserName(string: String)

    fun clearData()

}
