package com.iceman.ui.places

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.AutocompletePrediction
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import com.google.android.libraries.places.api.net.PlacesClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class AutocompleteViewModel(context : Context) : ViewModel() {
    private val placesClient: PlacesClient = Places.createClient(context)

    private val _predictions = MutableStateFlow<List<AutocompletePrediction>>(emptyList())
    val predictions: StateFlow<List<AutocompletePrediction>> = _predictions

    fun getAutocompletePredictions(query: String) {
        viewModelScope.launch {
            val request = FindAutocompletePredictionsRequest.builder()
                .setQuery(query)
                .setCountries("BR")
                .build()

            val response = placesClient.findAutocompletePredictions(request).await()
            _predictions.value = response.autocompletePredictions
        }
    }
    fun clearPredictions() { _predictions.value = emptyList() }
}
