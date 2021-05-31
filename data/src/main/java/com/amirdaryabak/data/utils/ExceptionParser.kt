package com.amirdaryabak.data.utils

import com.google.gson.Gson
import retrofit2.HttpException
import java.io.IOException

object ExceptionParser {

    private const val NO_INTERNET_CONNECTION = "اتصال شما به اینترنت برقرار نمی‌باشد"
    private const val SOMETHING_WENT_WRONG = "خطایی رخ داده است"
    private const val CODE_NO_ERROR = 0

    private val gson = Gson()

    fun getErrorMessage(t: Throwable): String {
        return when (t) {
            is IOException -> NO_INTERNET_CONNECTION
            is HttpException -> extractMessageFromHttpExceptionAllBody(t)
            else -> SOMETHING_WENT_WRONG
        }
    }

    fun getErrorCode(t: Throwable): Int {
        return when (t) {
            is IOException -> CODE_NO_ERROR
            is HttpException -> t.code()
            else -> CODE_NO_ERROR
        }
    }

    private fun extractMessageFromHttpExceptionOneField(httpException: HttpException): String {
        /*val errorBody: ResponseBody? = httpException.response()?.errorBody()
        return try {
            if (errorBody != null) {
                val type: Type = object : TypeToken<T>() {}.type
                val envelope: T = gson.fromJson(errorBody.charStream(), type)
                if (envelope.X?.isNotBlank() == true) envelope.title else SOMETHING_WENT_WRONG
            } else {
                SOMETHING_WENT_WRONG
            }
        } catch (t: Throwable) {
            SOMETHING_WENT_WRONG
        }*/
        return ""
    }

    private fun extractMessageFromHttpExceptionAllBody(httpException: HttpException): String {
        /*val errorBody: ResponseBody? = httpException.response()?.errorBody()
        return try {
            if (errorBody != null) {
                val type: Type = object : TypeToken<T>() {}.type
                val dynamicResponse: T =
                    gson.fromJson(errorBody.charStream(), type)
                if (dynamicResponse.title?.isNotBlank() == true) Gson().toJson(dynamicResponse) else SOMETHING_WENT_WRONG
            } else {
                SOMETHING_WENT_WRONG
            }
        } catch (t: Throwable) {
            SOMETHING_WENT_WRONG
        }*/
        return ""
    }

}