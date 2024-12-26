package com.rubtsov.port.myplaceweathernew.data.api

import com.rubtsov.port.myplaceweathernew.model.Weather
import retrofit2.Response

class WeatherRepository(private val apiService: ApiService) {
    suspend fun getWeather(lat: Double, lon: Double, appId: String): Response<Weather> {
        return apiService.getWeather(lat = lat, lon = lon, appid = appId)
    }
}
