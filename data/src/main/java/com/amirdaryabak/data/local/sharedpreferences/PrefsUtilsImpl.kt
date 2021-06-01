package com.amirdaryabak.data.local.sharedpreferences

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

private const val PREF_CODE = "PREF_CODE"

@Singleton
class PrefsUtilsImpl @Inject constructor(private val prefs: SharedPreferences) : PrefsUtils {

    override fun haveCode() = getCode() != ""

    override fun setCode(string: String) = prefs.edit().putString(PREF_CODE, string).apply()
    override fun getCode(): String = prefs.getString(PREF_CODE, "") ?: ""

    override fun clearData() = prefs.edit().clear().apply()

}
