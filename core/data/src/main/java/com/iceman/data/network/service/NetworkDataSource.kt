package com.iceman.data.network.service

import com.iceman.data.network.request.ConfirmTripRequest
import com.iceman.data.network.request.EstimateTripRequest
import com.iceman.data.network.response.ApiResponse
import com.iceman.data.network.response.ConfirmTrip
import com.iceman.data.network.response.EstimateTrip
import com.iceman.data.network.response.RideOverview


interface NetworkDataSource {
    /**Responsável por receber a origem e o destino da viagem e realizar os
    cálculos dos valores da viagem.
     */
    suspend fun estimateTrip(estimateTripRequest: EstimateTripRequest): EstimateTrip

    /**
    Responsável por confirmar a viagem.
     */

  suspend  fun confirmRide(confirmTripRequest: ConfirmTripRequest): ConfirmTrip

    /**
    Responsável por listar as viagens realizadas por um determinado
    usuário.
     */
    suspend fun getRides(customerId: String,id: String): RideOverview

}