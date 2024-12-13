package com.iceman.domain.model

import com.iceman.data.network.response.EstimateTrip

data class ScreenState(
    val origin: String ="",
    val destination : String ="",
    val userId : String,
    val estimateTrip: EstimateTrip
    ) {

}