package com.iceman.data.repository

import android.util.Log
import com.iceman.data.network.request.ConfirmTripRequest
import com.iceman.data.network.request.EstimateTripRequest
import com.iceman.data.network.response.ApiResponse
import com.iceman.data.network.response.ConfirmTrip
import com.iceman.data.network.response.EstimateTrip
import com.iceman.data.network.response.RideOverview
import com.iceman.data.network.service.NetworkDataSource
import com.iceman.data.network.util.ApiException
import com.iceman.data.network.util.parseError
import kotlinx.coroutines.flow.flow

class RideRepository(private val networkApi: NetworkDataSource) {

    fun getRides(
        customerId: String,
        driverId: String
    ) = flow<ApiResponse<RideOverview>> {
        try {
            emit(
                ApiResponse.OnSuccess(
                    networkApi.getRides(customerId, driverId)
                )
            )
        } catch (e: ApiException) {
            val error = parseError(e)
            emit(
                ApiResponse.OnError(
                    errorCode = error.errorCode,
                    errorDescription = error.errorDescription
                )
            )
        }
    }

    fun confirmRide(confirmTripRequest: ConfirmTripRequest) =
        flow<ApiResponse<ConfirmTrip>> {
            try {
                emit(
                    ApiResponse.OnSuccess(
                        networkApi.confirmRide(confirmTripRequest)
                    )
                )
            } catch (e: ApiException) {
                val error = parseError(e)
                emit(
                    ApiResponse.OnError(
                        errorCode = error.errorCode,
                        errorDescription = error.errorDescription
                    )
                )
            }
        }

    fun estimateTripValue(estimateTripRequest: EstimateTripRequest) =
        flow<ApiResponse<EstimateTrip>> {
            try {
                emit(
                    ApiResponse.OnSuccess(networkApi.estimateTrip(estimateTripRequest))
                )
            } catch (e: ApiException) {
                val error = parseError(e)
                emit(
                    ApiResponse.OnError(
                        errorCode = error.errorCode,
                        errorDescription = error.errorDescription
                    )
                )
            }
        }
}