package com.amirdaryabak.data.local.sharedpreferences


interface PrefsUtils {

    fun haveCode(): Boolean

    fun getCode(): String
    fun setCode(string: String)

    fun getToken(): String
    fun setToken(string: String)

    fun clearData()

}
