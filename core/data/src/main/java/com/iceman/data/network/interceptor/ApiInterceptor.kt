package com.iceman.data.network.interceptor

import com.iceman.data.network.util.ApiException
import okhttp3.Interceptor
import okhttp3.Response

class ApiInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        if (!response.isSuccessful) {
            val errorBody = response.body?.string() ?: "An error has occurred"
            throw ApiException(errorBody,response.code)
        }
        return response
    }
}
