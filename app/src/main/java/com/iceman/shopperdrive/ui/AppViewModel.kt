package com.iceman.shopperdrive.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iceman.domain.model.ScreenState
import com.iceman.home.EmptyEstimateState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AppViewModel : ViewModel() {
    private val _screenState: MutableStateFlow<ScreenState> = MutableStateFlow(EMPTY_SCREEN_STATE)
     val screenState: StateFlow<ScreenState> = _screenState.asStateFlow()
    fun updateScreenState(screenState: ScreenState){
        viewModelScope.launch{
            _screenState.update { screenState }
        }
    }
}




private val EMPTY_SCREEN_STATE = ScreenState("","","", estimateTrip = EmptyEstimateState)