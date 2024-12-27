package com.rubtsov.port.myplaceweathernew.presentation.screens.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.rubtsov.port.myplaceweathernew.R
import com.rubtsov.port.myplaceweathernew.domain.getMoonPhaseText
import com.rubtsov.port.myplaceweathernew.model.Current
import com.rubtsov.port.myplaceweathernew.model.Daily
import com.rubtsov.port.myplaceweathernew.model.FeelsLike
import com.rubtsov.port.myplaceweathernew.model.Temp
import com.rubtsov.port.myplaceweathernew.model.Weather
import com.rubtsov.port.myplaceweathernew.model.WeatherX
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun WeatherContent(weather: Weather) {
    val current = weather.current
    val date = remember(current.dt) {
        SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault()).format(Date(current.dt * 1000L))
    }
    val pressure = weather.current.pressure
    val moonPhase = weather.daily.firstOrNull()?.moon_phase ?: 0.0
    val moonPhaseText = getMoonPhaseText(moonPhase)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Date: $date",
            fontFamily = FontFamily(
                Font(R.font.inter_bold, weight = FontWeight.Thin)
            ),
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Card(
            border = BorderStroke(width = 1.dp, Color.Gray),
            colors = CardDefaults.cardColors(containerColor = Color.Transparent),
            shape = RoundedCornerShape(size = 4.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 6.dp, horizontal = 12.dp)
        ) {
            Column(
                modifier = Modifier.padding(10.dp)
            ) {
                Row(
                    modifier = Modifier
                        .height(IntrinsicSize.Min)
                ) {
                    Column {
                        Text(
                            text = "Температура: ",
                            fontFamily = FontFamily(
                                Font(R.font.inter_bold, weight = FontWeight.Thin)
                            ),
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Вологість: ",
                            fontFamily = FontFamily(
                                Font(R.font.inter_bold, weight = FontWeight.Thin)
                            ),
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Швидкість вітру: ",
                            fontFamily = FontFamily(
                                Font(R.font.inter_bold, weight = FontWeight.Thin)
                            ),
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Тиск:",
                            fontFamily = FontFamily(
                                Font(R.font.inter_bold, weight = FontWeight.Thin)
                            ),
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.width(15.dp))

                    Column {
                        Spacer(
                            modifier = Modifier
                                .fillMaxHeight()
                                .width(1.dp)
                                .background(Color.Black)
                        )
                    }

                    Spacer(modifier = Modifier.width(15.dp))

                    Column {
                        Text(
                            text = "${current.temp}°C",
                            fontFamily = FontFamily(
                                Font(R.font.roboto_thin, weight = FontWeight.Thin)
                            ),
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "${current.humidity}%",
                            fontFamily = FontFamily(
                                Font(R.font.roboto_thin, weight = FontWeight.Thin)
                            ),
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "${current.wind_speed} м/с",
                            fontFamily = FontFamily(
                                Font(R.font.roboto_thin, weight = FontWeight.Thin)
                            ),
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "$pressure кПа",
                            fontFamily = FontFamily(
                                Font(R.font.roboto_thin, weight = FontWeight.Thin)
                            ),
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        current.weather.firstOrNull()?.icon?.let { iconCode ->
                            val iconUrl = "https://openweathermap.org/img/wn/${iconCode}@2x.png"
                            Image(
                                painter = rememberAsyncImagePainter(iconUrl),
                                contentDescription = "Weather Icon",
                                modifier = Modifier
                                    .size(80.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(Color.LightGray),
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                    Box(
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        Image(
                            painter = painterResource(R.drawable.m_3),
                            contentDescription = "",
                            modifier = Modifier
                                .size(80.dp)
                                .clip(RoundedCornerShape(8.dp)),
                            contentScale = ContentScale.Crop
                        )
                    }
                    Box(
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        Image(
                            painter = painterResource(R.drawable.m_3),
                            contentDescription = "",
                            modifier = Modifier
                                .size(80.dp)
                                .clip(RoundedCornerShape(8.dp)),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WeatherScreenPreview() {
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
                rain = null,
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
                wind_speed = 6.0
            )
        ),
        hourly = emptyList(),
        lat = 41.14961,
        lon = -8.61099,
        minutely = emptyList(),
        timezone = "Europe/Lisbon",
        timezone_offset = 3600
    )

    WeatherContent(weather = mockWeather)
}

