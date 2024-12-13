package com.iceman.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.serialization.Serializable

@Serializable
data class Coordinates(@Json(name = "latitude") val latitude: Double, @Json(name = "longitude") val longitude: Double)
