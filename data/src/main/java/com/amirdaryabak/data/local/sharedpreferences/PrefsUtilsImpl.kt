package com.amirdaryabak.data.local.sharedpreferences

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

private const val PREF_TOKEN = "PREF_TOKEN"

@Singleton
class PrefsUtilsImpl @Inject constructor(private val prefs: SharedPreferences) : PrefsUtils {

    override fun setToken(string: String) = prefs.edit().putString(PREF_TOKEN, string).apply()
    override fun getToken(): String = prefs.getString(PREF_TOKEN, "") ?: ""

    override fun clearData() = prefs.edit().clear().apply()

}
