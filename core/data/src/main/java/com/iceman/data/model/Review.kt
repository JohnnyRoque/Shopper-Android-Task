package com.iceman.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Review(val rating : Double,val comment : String = "")
