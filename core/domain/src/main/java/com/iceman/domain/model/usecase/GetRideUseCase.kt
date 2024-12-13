package com.iceman.domain.model.usecase

import com.iceman.data.network.response.ApiResponse
import com.iceman.data.network.response.RideOverview
import com.iceman.data.repository.RideRepository
import kotlinx.coroutines.flow.Flow

class GetRideUseCase(val repository: RideRepository) {
    fun execute(customerId: String, driverId: String): Flow<ApiResponse<RideOverview>> {
        return repository.getRides(customerId = customerId, driverId = driverId)
    }
}