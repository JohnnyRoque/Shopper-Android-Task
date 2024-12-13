package com.iceman.data.network.request

import com.iceman.data.model.Driver
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EstimateTripRequest(
    @SerialName("customer_id") val customerId: String,
    @SerialName("origin") val origin: String,
    @SerialName("destination") val destination: String
)

@Serializable
data class ConfirmTripRequest(
    @SerialName("customer_id") val customerId: String = "",
    @SerialName("origin") val origin: String = "",
    @SerialName("destination") val destination: String = "",
    @SerialName("distance") val distance: Double = 0.0,
    @SerialName("driver") val driver: Driver,
    @SerialName("duration") val duration: String = "",
    @SerialName("value") val value: Double = 0.0
)

