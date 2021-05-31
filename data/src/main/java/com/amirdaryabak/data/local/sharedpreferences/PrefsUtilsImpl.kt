package com.amirdaryabak.data.local.sharedpreferences

import android.content.SharedPreferences

private const val PREF_CONST = "PREF_CONST"

class PrefsUtilsImpl constructor(private val prefs: SharedPreferences) : PrefsUtils {

    override fun set(string: String?) = prefs.edit().putString(PREF_CONST, string).apply()
    override fun get(): String? = prefs.getString(PREF_CONST, null)

    override fun clearData() = prefs.edit().clear().apply()

}
