package com.iceman.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Driver(
    val id : Int,
    val name : String,

)
