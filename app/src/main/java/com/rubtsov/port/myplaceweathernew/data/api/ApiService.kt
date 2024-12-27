package com.rubtsov.port.myplaceweathernew.data.api

import com.rubtsov.port.myplaceweathernew.model.Weather
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("data/3.0/onecall") // Правильна кінцева точка
    suspend fun getWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") appid: String,
        @Query("units") units: String = "metric",
        @Query("lang") lang: String = "ua"
    ): Response<Weather>
}
