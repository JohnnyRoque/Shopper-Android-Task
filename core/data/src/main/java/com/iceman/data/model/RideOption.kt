package com.iceman.data.model

import com.squareup.moshi.JsonClass
import kotlinx.serialization.Serializable

@Serializable
data class RideOption(
    val id : Int,
    val name : String,
    val description: String,
    val vehicle: String,
    val review: Review,
    val value: Double
)
