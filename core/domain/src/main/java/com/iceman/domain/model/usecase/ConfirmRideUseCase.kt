package com.iceman.domain.model.usecase

import com.iceman.data.network.request.ConfirmTripRequest
import com.iceman.data.repository.RideRepository

class ConfirmRideUseCase(private val repository: RideRepository) {
    fun execute( confirmTripRequest: ConfirmTripRequest) = repository.confirmRide(confirmTripRequest)

}