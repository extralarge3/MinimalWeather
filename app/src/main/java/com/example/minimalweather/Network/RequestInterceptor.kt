package com.example.minimalweather.Network

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response


class RequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request().url
            .newBuilder()
            .addQueryParameter("appid", "3795855f47f8ab9d6953f02f1fc6f4bb") //hardcoded for test
            .build()
        val request = chain.request().newBuilder().url(url).build()
        return chain.proceed(request)
    }
}