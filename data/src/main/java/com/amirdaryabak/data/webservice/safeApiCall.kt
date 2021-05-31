package com.amirdaryabak.data.webservice

import com.amirdaryabak.data.utils.ExceptionParser
import com.amirdaryabak.data.utils.Resource
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import timber.log.Timber
import java.lang.reflect.Type


suspend inline fun <T> safeApiCall(
    crossinline callFunction: suspend () -> T
): Resource<T> {
    return try {
        val response = callFunction.invoke()
        Resource.success(response)
    } catch (t: Throwable) {
        Timber.d("SafeApi Throwable: %s", t)
        val errorCode = ExceptionParser.getErrorCode(t)
        val errorJson = if (errorCode != 404) ExceptionParser.getErrorMessage(t) else "خطایی رخ داده است"

        val type: Type = object : TypeToken<T>() {}.type
        val dynamicResponse: T? =
            if (errorCode != 404 && errorCode != 400 && errorCode != 0 && errorJson != "خطایی رخ داده است") {
                Gson().fromJson(errorJson, type)
        } else {
            null
            }

        /*ResourceTest.error(
            if (errorCode != 404 && errorCode != 400 && errorCode != 0) {
                dynamicResponse?.X
            } else if (errorCode == 0) {
                "اتصال شما به اینترنت برقرار نمی‌باشد"
            } else {
                "خطایی رخ داده است"
            },
            null, errorCode
        )*/
        Resource.error(null, null)
    }

}