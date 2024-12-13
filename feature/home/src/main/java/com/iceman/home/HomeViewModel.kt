package com.iceman.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iceman.data.model.Coordinates
import com.iceman.data.network.request.EstimateTripRequest
import com.iceman.data.network.response.ApiResponse
import com.iceman.data.network.response.EstimateTrip
import com.iceman.data.network.response.RideOverview
import com.iceman.domain.model.ScreenState
import com.iceman.domain.model.usecase.EstimateTripValueUseCase
import com.iceman.domain.model.usecase.GetRideUseCase
import com.iceman.ui.DESTINATION
import com.iceman.ui.ORIGIN
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    val getRideUseCase: GetRideUseCase,
    val estimateTripValueUseCase: EstimateTripValueUseCase
) : ViewModel() {

    private val _screenState : MutableStateFlow<ScreenState> = MutableStateFlow(ScreenState("","","",EmptyEstimateState))
     val screenState : StateFlow<ScreenState> = _screenState.asStateFlow()

    fun updateScreenState(newState: ScreenState){
        _screenState.update { newState }
    }





    private var _originTextFieldValue by mutableStateOf(TextFieldValue(""))
    val originTextFieldValue get() = _originTextFieldValue

    private var _destinationTextFieldValue by mutableStateOf(TextFieldValue(""))
    val destinationTextFieldValue get() = _destinationTextFieldValue

    private var _iDTextFieldValue by mutableStateOf(TextFieldValue(""))
    val iDTextFieldValue get() = _iDTextFieldValue


    fun updateUserText(type: String, newText: TextFieldValue) {
        when (type) {
            DESTINATION -> _destinationTextFieldValue = newText
            ORIGIN -> _originTextFieldValue = newText
            else -> _iDTextFieldValue = newText
        }
    }




    private val _rideOverviewState: MutableStateFlow<RideOverview> =
        MutableStateFlow(EmptyRideOverview)
    val rideOverviewState: StateFlow<RideOverview> = _rideOverviewState.asStateFlow()

    private val _estimateTripState: MutableStateFlow<EstimateTrip> =
        MutableStateFlow(EmptyEstimateState)
    val estimateTripState: StateFlow<EstimateTrip> = _estimateTripState.asStateFlow()

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

    fun fetchRides(customerId: String, driverId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getRideUseCase.execute(customerId, driverId).collect { response ->
                handleApiResponse(response) { rideOverview ->
                    _rideOverviewState.value = rideOverview
                }
            }
        }
    }

    fun estimateTripValue() {
        viewModelScope.launch(Dispatchers.IO) {
            val estimateTripRequest = EstimateTripRequest(
                customerId = iDTextFieldValue.text,
                origin = originTextFieldValue.text,
                destination = destinationTextFieldValue.text
            )
            Log.d("responseState", "estimateTripRequest: ${estimateTripRequest}")

            estimateTripValueUseCase.execute(estimateTripRequest).collect { response ->
                handleApiResponse(response) { estimateTripStateResponse ->
                    Log.d("responseState", "$estimateTripStateResponse")
                    _estimateTripState.update { estimateTripStateResponse }
                }
            }
        }
    }
}

private val EmptyRideOverview = RideOverview("NONE", emptyList())
val EmptyEstimateState = EstimateTrip(Coordinates(0.0, 0.0), Coordinates(0.0, 0.0), 0.0, 0, emptyList(),
)
