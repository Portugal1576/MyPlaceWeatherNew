package com.rubtsov.port.myplaceweathernew.presentation.screens.detail

sealed class WeatherIntent {
    data object FetchWeather : WeatherIntent()
}