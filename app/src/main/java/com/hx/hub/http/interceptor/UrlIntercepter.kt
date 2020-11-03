package com.hx.hub.http.interceptor

import com.hx.hub.di.BASE_TRENDING_URL
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.Interceptor
import okhttp3.Response

class UrlIntercepter : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val oldHttpUrl = request.url
        val builder = request.newBuilder()
        val headerValues = request.headers("url_name")
        if (headerValues != null && headerValues.isNotEmpty()) {
            builder.removeHeader("url_name")
            var newBaseUrl: HttpUrl? = when (headerValues[0]) {
                "trending" -> {
                    BASE_TRENDING_URL.toHttpUrlOrNull()
                }
                else -> {
                    oldHttpUrl
                }
            }
            val newFullUrl = newBaseUrl?.let {
                oldHttpUrl
                        .newBuilder()
                        .scheme(newBaseUrl.scheme)
                        .host(newBaseUrl.host)
                        .port(newBaseUrl.port)
                        .build()
            }
            return chain.proceed(builder.url(newFullUrl ?: oldHttpUrl).build())
        }
        return chain.proceed(request)
    }

}