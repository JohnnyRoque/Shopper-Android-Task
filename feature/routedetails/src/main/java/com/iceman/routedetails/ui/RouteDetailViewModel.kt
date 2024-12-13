package com.iceman.routedetails.ui

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iceman.data.model.Coordinates
import com.iceman.data.network.request.ConfirmTripRequest
import com.iceman.data.network.response.ApiResponse
import com.iceman.data.network.response.ConfirmTrip
import com.iceman.domain.model.usecase.ConfirmRideUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RouteDetailViewModel(
    private val confirmRideUseCase: ConfirmRideUseCase
) :
    ViewModel() {
        private val _confirmRideState : MutableStateFlow<ConfirmTrip> = MutableStateFlow(ConfirmTrip(false))
     val confirmRideState : StateFlow<ConfirmTrip> = _confirmRideState.asStateFlow()

    fun confirmRide(confirmTripRequest: ConfirmTripRequest) {
        viewModelScope.launch(Dispatchers.IO)
        {
            confirmRideUseCase.execute(confirmTripRequest).collect {
                    handleApiResponse(it){
                        Log.d("ConfirmState", "${it.success}")
                        _confirmRideState.value =  it
                    }
            }
        }
    }

    fun getLocationRoute(origin: Coordinates, destination: Coordinates): Uri {
        return Uri.parse(
            "https://maps.googleapis.com/maps/api/staticmap?size=600x400&path=color:0x0000ff|weight:5|${origin.latitude},${origin.longitude}|${destination.latitude},${destination.longitude}&key=${"MAPS_KEY"}"
        )
    }

    private fun <T> handleApiResponse(
        response: ApiResponse<T>,
        onSuccess: (T) -> Unit
    ) {
        when (response) {
            is ApiResponse.OnError -> {
                val error = "${response.errorCode}: ${response.errorDescription}"
                Log.w("HomeViewModel", error)
            }

            is ApiResponse.OnSuccess -> {
                onSuccess(response.data)
            }
        }
    }
}