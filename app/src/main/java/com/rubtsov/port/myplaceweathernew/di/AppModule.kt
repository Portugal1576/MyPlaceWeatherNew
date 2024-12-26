package com.rubtsov.port.myplaceweathernew.di

import com.rubtsov.port.myplaceweathernew.data.api.ApiService
import com.rubtsov.port.myplaceweathernew.data.api.RetrofitInstance
import com.rubtsov.port.myplaceweathernew.data.api.WeatherRepository
import com.rubtsov.port.myplaceweathernew.model.Coordinates
import com.rubtsov.port.myplaceweathernew.presentation.screens.choice.WeatherViewModel
import com.rubtsov.port.myplaceweathernew.presentation.screens.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    // Retrofit та ApiService
    single<ApiService> { RetrofitInstance.api }

    // Репозиторій
    single { WeatherRepository(get()) }

    // WeatherViewModel
    viewModel { (coordinates: Coordinates) ->
        WeatherViewModel(
            repository = get(),
            appId = getProperty("OPENWEATHERMAP_API_KEY"),
            coordinates = coordinates
        )
    }

    // SplashScreenViewModel
    viewModel { MainViewModel() }
}
