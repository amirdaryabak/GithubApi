package com.amirdaryabak.data.local.sharedpreferences

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

private const val PREF_TOKEN = "PREF_TOKEN"
private const val PREF_USER_NAME = "PREF_USER_NAME"

@Singleton
class PrefsUtilsImpl @Inject constructor(private val prefs: SharedPreferences) : PrefsUtils {

    override fun setToken(string: String) = prefs.edit().putString(PREF_TOKEN, string).apply()
    override fun getToken(): String = prefs.getString(PREF_TOKEN, "") ?: ""

    override fun setUserName(string: String) = prefs.edit().putString(PREF_USER_NAME, string).apply()
    override fun getUserName(): String = prefs.getString(PREF_USER_NAME, "") ?: ""

    override fun clearData() = prefs.edit().clear().apply()

}
