package com.amirdaryabak.data.local.sharedpreferences


interface PrefsUtils {

    fun getToken(): String
    fun setToken(string: String)

    fun clearData()

}
