package com.rubtsov.port.myplaceweathernew.presentation.screens.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.rubtsov.port.myplaceweathernew.model.Coordinates
import com.rubtsov.port.myplaceweathernew.model.Current
import com.rubtsov.port.myplaceweathernew.model.Daily
import com.rubtsov.port.myplaceweathernew.model.FeelsLike
import com.rubtsov.port.myplaceweathernew.model.Temp
import com.rubtsov.port.myplaceweathernew.model.Weather
import com.rubtsov.port.myplaceweathernew.model.WeatherX
import com.rubtsov.port.myplaceweathernew.presentation.navigation.Screen
import com.rubtsov.port.myplaceweathernew.presentation.screens.components.DailyForecastItem
import com.rubtsov.port.myplaceweathernew.presentation.screens.components.DisplayCoordinates
import com.rubtsov.port.myplaceweathernew.presentation.screens.components.ErrorContent
import com.rubtsov.port.myplaceweathernew.presentation.screens.components.LoadingContent
import com.rubtsov.port.myplaceweathernew.presentation.screens.components.WeatherContent
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun DetailScreenRoot(
    navController: NavHostController,
    coordinates: Coordinates
) {
    val weatherViewModel: WeatherViewModel = koinViewModel(
        parameters = { parametersOf(coordinates) }
    )
    DetailScreen(
        navigate = { destinationScreen ->
            navController.navigate(destinationScreen)
        },
        coordinates = coordinates,
        weatherViewModel = weatherViewModel
    )
}

@Composable
fun DetailScreen(
    navigate: (destinationScreen: Screen) -> Unit,
    coordinates: Coordinates,
    weatherViewModel: WeatherViewModel
) {
    LaunchedEffect(Unit) {
        weatherViewModel.handleIntent(WeatherIntent.FetchWeather)
    }

    val viewState by weatherViewModel.viewState.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        when (val state = viewState) {
            is WeatherViewState.Loading -> LoadingContent()
            is WeatherViewState.Success -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .windowInsetsPadding(WindowInsets.systemBars)
                        .verticalScroll(rememberScrollState())
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    DisplayCoordinates(
                        coordinates.lat,
                        coordinates.lon,
                        color = Color.Black,
                        fontSize = 20.sp
                    )

                    WeatherContent(weather = state.weather)

                    LazyColumn(
                        modifier = Modifier.weight(1f)
                    ) {
                        items(state.weather.daily) { day ->
                            DailyForecastItem(day)
                        }
                    }
                }
            }

            is WeatherViewState.Error -> ErrorContent(errorMessage = state.error)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDetailScreen() {
    val mockCoordinates = Coordinates(lat = 41.14961, lon = -8.61099)

    val mockWeather = Weather(
        current = Current(
            clouds = 50,
            dew_point = 10.5,
            dt = 1672531200,
            feels_like = 22.0,
            humidity = 60,
            pressure = 1013,
            sunrise = 1672508400,
            sunset = 1672546800,
            temp = 24.0,
            uvi = 5.0,
            visibility = 10000,
            weather = listOf(
                WeatherX(
                    description = "Clear sky",
                    icon = "01d",
                    id = 800,
                    main = "Clear"
                )
            ),
            wind_deg = 90,
            wind_speed = 5.5
        ),
        daily = listOf(
            Daily(
                clouds = 20,
                dew_point = 9.0,
                dt = 1672531200,
                feels_like = FeelsLike(
                    day = 25.0, eve = 22.0, morn = 20.0, night = 18.0
                ),
                humidity = 50,
                moon_phase = 0.5,
                moonrise = 1672512000,
                moonset = 1672561200,
                pop = 0.0,
                pressure = 1015,
                summary = "Sunny",
                sunrise = 1672508400,
                sunset = 1672546800,
                temp = Temp(
                    day = 25.0, eve = 22.0, max = 26.0, min = 18.0, morn = 20.0, night = 18.0
                ),
                uvi = 5.0,
                weather = listOf(
                    WeatherX(
                        description = "Sunny",
                        icon = "01d",
                        id = 800,
                        main = "Clear"
                    )
                ),
                wind_deg = 100,
                wind_gust = 10.0,
                wind_speed = 6.0,
                rain = null
            )
        ),
        hourly = emptyList(),
        lat = 41.14961,
        lon = -8.61099,
        minutely = emptyList(),
        timezone = "Europe/Lisbon",
        timezone_offset = 3600
    )

    val state = WeatherViewState.Success(mockWeather)

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.systemBars)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            DisplayCoordinates(
                lat = mockCoordinates.lat,
                lng = mockCoordinates.lon,
                color = Color.Black,
                fontSize = 20.sp
            )

            WeatherContent(weather = mockWeather)

            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                items(mockWeather.daily) { day ->
                    DailyForecastItem(day)
                }
            }
        }
    }
}
