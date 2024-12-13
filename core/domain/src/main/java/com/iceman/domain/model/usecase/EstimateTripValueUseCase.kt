package com.iceman.domain.model.usecase

import com.iceman.data.network.request.EstimateTripRequest
import com.iceman.data.network.response.ApiResponse
import com.iceman.data.network.response.EstimateTrip
import com.iceman.data.repository.RideRepository
import kotlinx.coroutines.flow.Flow

class EstimateTripValueUseCase(private val repository: RideRepository) {
    fun execute(estimateTripRequest: EstimateTripRequest): Flow<ApiResponse<EstimateTrip>> {
        return repository.estimateTripValue(estimateTripRequest)
    }
}