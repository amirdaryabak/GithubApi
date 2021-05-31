package com.amirdaryabak.data.local.sharedpreferences


interface PrefsUtils {

    fun get(): String?
    fun set(string: String?)

    fun clearData()

}
