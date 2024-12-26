package com.rubtsov.port.myplaceweathernew.presentation.screens.choice

sealed class WeatherIntent {
    data object FetchWeather : WeatherIntent()
}