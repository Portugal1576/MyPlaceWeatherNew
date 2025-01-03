package com.rubtsov.port.myplaceweathernew.presentation.screens.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rubtsov.port.myplaceweathernew.data.api.WeatherRepository
import com.rubtsov.port.myplaceweathernew.model.Coordinates
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val repository: WeatherRepository,
    private val appId: String,
    private val coordinates: Coordinates
) : ViewModel() {

    private val _viewState = MutableStateFlow<WeatherViewState>(WeatherViewState.Loading)
    val viewState: StateFlow<WeatherViewState> = _viewState

    fun handleIntent(intent: WeatherIntent) {
        when (intent) {
            is WeatherIntent.FetchWeather -> fetchWeather()
        }
    }

    private fun fetchWeather() {
        viewModelScope.launch {
            _viewState.value = WeatherViewState.Loading
            try {
                val response = repository.getWeather(coordinates.lat, coordinates.lon, appId)
                Log.d("MyLog", "API Key: $appId")
                if (response.isSuccessful) {
                    response.body()?.let {
                        _viewState.value = WeatherViewState.Success(it)
                    } ?: run {
                        _viewState.value = WeatherViewState.Error("No data available")
                    }
                } else {
                    _viewState.value = WeatherViewState.Error("Error: ${response.message()}")
                }
            } catch (e: Exception) {
                _viewState.value = WeatherViewState.Error(e.localizedMessage ?: "An error occurred")
            }
        }
    }
}
