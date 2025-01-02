package com.rubtsov.port.myplaceweathernew.presentation.screens.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.rubtsov.port.myplaceweathernew.domain.getSeasonImageResource
import com.rubtsov.port.myplaceweathernew.model.Coordinates
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

    val seasonImage = remember { getSeasonImageResource() }

    Box(modifier = Modifier.fillMaxSize()) {
        when (val state = viewState) {
            is WeatherViewState.Loading -> LoadingContent()
            is WeatherViewState.Success -> {

                Image(
                    painter = painterResource(id = seasonImage),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillBounds
                )

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .windowInsetsPadding(WindowInsets.systemBars)
                        .verticalScroll(rememberScrollState())
                        .padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    DisplayCoordinates(
                        coordinates.lat,
                        coordinates.lon,
                        color = Color.Red,
                        fontSize = 20.sp
                    )

                    BoxWithConstraints(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        val itemWidth = maxWidth

                        LazyRow(
                            modifier = Modifier
                                .fillMaxSize(),
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            items(state.weather.hourly) { hourly ->
                                WeatherContent(
                                    hourly = hourly,
                                    modifier = Modifier
                                        .width(itemWidth)
                                )
                            }
                        }
                    }

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
