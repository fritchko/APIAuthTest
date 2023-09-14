package com.example.apiauthtest.retrofit

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val newRequest = originalRequest.newBuilder()
            .header("key","9c8c0eb6b72342dfb6435321231309")
            .build()

        return chain.proceed(newRequest)
    }
}