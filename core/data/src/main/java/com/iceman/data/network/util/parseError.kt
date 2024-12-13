package com.iceman.data.network.util

import com.iceman.data.network.response.ApiResponse
import com.squareup.moshi.Moshi

fun parseError(e: ApiException): ApiResponse.OnError {
    // Parse the errorBody from the ApiException into OnError object
    val errorBody = e.errorBody
    return try {
        // Use Moshi, Gson, or another library to parse errorBody JSON into OnError
        val adapter = Moshi.Builder().build().adapter(ApiResponse.OnError::class.java)
        adapter.fromJson(errorBody) ?: ApiResponse.OnError()
    } catch (_: Exception) {
        ApiResponse.OnError(errorDescription = e.errorBody)
    }
}