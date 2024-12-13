package com.iceman.data.network.response


import com.iceman.data.model.Coordinates
import com.iceman.data.model.Ride
import com.iceman.data.model.RideOption
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EstimateTrip(
    @SerialName("origin") val origin: Coordinates,
    @SerialName("destination") val destination: Coordinates,
    @SerialName("distance") val distance: Double,
    @SerialName("duration") val duration: Int,
    @SerialName("options") val options: List<RideOption>,
    )

@Serializable
data class ConfirmTrip(val success: Boolean)

@Serializable
data class RideOverview(
     val customerId: String,
     val rides: List<Ride>
)

@Serializable
sealed class ApiResponse<out T> {

    @Serializable
    data class OnSuccess<out T>(val data: T): ApiResponse<T>()

    @Serializable
    data class OnError(
        @SerialName("error_code") val errorCode: String = "INVALID_DATA",
        @SerialName("error_description") val errorDescription: String = ""
    ) : ApiResponse<Nothing>()
}






