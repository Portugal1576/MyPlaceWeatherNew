package com.rubtsov.port.myplaceweathernew.presentation.screens.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.rubtsov.port.myplaceweathernew.model.Coordinates
import com.rubtsov.port.myplaceweathernew.presentation.navigation.Screen
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
    // Запуск фетчу погоди під час композиції
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
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {

                    Spacer(modifier = Modifier.height(30.dp))

                    DisplayCoordinates(
                        coordinates.lat,
                        coordinates.lon,
                        color = Color.Black,
                        fontSize = 24.sp
                    )

                    WeatherContent(weather = state.weather)
                }
            }

            is WeatherViewState.Error -> ErrorContent(errorMessage = state.error)
        }
    }
}
