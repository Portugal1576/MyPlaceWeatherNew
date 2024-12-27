package com.rubtsov.port.myplaceweathernew.presentation.screens.detail

import com.rubtsov.port.myplaceweathernew.model.Weather

sealed class WeatherViewState {
    data object Loading : WeatherViewState()
    data class Success(val weather: Weather) : WeatherViewState()
    data class Error(val error: String) : WeatherViewState()
}