package com.hx.hub.utils

import com.hx.hub.base.Results
import com.hx.hub.http.Errors
import retrofit2.Response
import java.io.IOException

inline fun <T> processApiResponse(request: () -> Response<T>): Results<T> {
    return try {
        val response = request()
        val responseCode = response.code()
        val responseMessage = response.message()
        if (response.isSuccessful) {
            Results.success(response.body()!!)
        } else {
            Results.failure(Errors.NetworkError(responseCode, responseMessage))
        }
    } catch (e: IOException) {
        Results.failure(Errors.NetworkError())
    }
}