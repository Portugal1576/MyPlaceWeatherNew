package com.rubtsov.port.myplaceweathernew.presentation.screens.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.rubtsov.port.myplaceweathernew.R
import com.rubtsov.port.myplaceweathernew.domain.getMoonPhaseText
import com.rubtsov.port.myplaceweathernew.model.Daily
import com.rubtsov.port.myplaceweathernew.model.FeelsLike
import com.rubtsov.port.myplaceweathernew.model.Temp
import com.rubtsov.port.myplaceweathernew.model.WeatherX
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun DailyForecastItem(daily: Daily) {
    val moonPhase = getMoonPhaseText(daily.moon_phase)

    Card(
        border = BorderStroke(width = 1.dp, Color.Red),
        shape = RoundedCornerShape(size = 8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.padding(8.dp)) {

                Text(
                    text = remember(daily.dt) {
                        SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault())
                            .format(Date(daily.dt * 1000L))
                    },
                    fontWeight = FontWeight.Bold
                )

                Text(text = "${stringResource(R.string.temp_max)}: ${daily.temp.max}°C")

                Text(text = "${stringResource(R.string.temp_min)}: ${daily.temp.min}°C")

                Text(text = "${stringResource(R.string.pressure)}: ${daily.pressure} кПА")

                Text(text = "${stringResource(R.string.moon_phase)}: $moonPhase")

                Text(
                    text = "${stringResource(R.string.desc)}: ${daily.weather.firstOrNull()?.description ?: "N/A"}",
                    fontWeight = FontWeight.Bold
                )
            }

            Column(
                modifier = Modifier
                    .padding(end = 20.dp)
            ) {

                WindDirection(
                    degree = daily.wind_deg,
                    Modifier
                        .padding(end = 10.dp)
                        .size(40.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    40
                )
                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = "${daily.wind_speed} м/с",
                    fontSize = 10.sp
                )

                Spacer(modifier = Modifier.height(5.dp))

                daily.weather.firstOrNull()?.icon?.let { iconCode ->
                    val iconUrl = "https://openweathermap.org/img/wn/${iconCode}@2x.png"
                    Image(
                        painter = rememberAsyncImagePainter(iconUrl),
                        contentDescription = "Weather Icon",
                        modifier = Modifier
                            .size(40.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color.LightGray),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDailyForecastItem() {

    val mockDaily = Daily(
        dt = 1672531200,
        sunrise = 1672508400,
        sunset = 1672546800,
        moonrise = 1672512000,
        moonset = 1672561200,
        moon_phase = 0.5,
        summary = "Mock summary",
        temp = Temp(
            day = 25.0,
            min = 18.0,
            max = 26.0,
            night = 18.0,
            eve = 22.0,
            morn = 20.0
        ),
        feels_like = FeelsLike(
            day = 25.0,
            night = 18.0,
            eve = 22.0,
            morn = 20.0
        ),
        pressure = 1013,
        humidity = 60,
        dew_point = 10.5,
        wind_speed = 3.4,
        wind_deg = 225,
        wind_gust = 5.5,
        weather = listOf(
            WeatherX(
                id = 800,
                main = "Clear",
                description = "clean",
                icon = "01d"
            )
        ),
        clouds = 50,
        pop = 0.0,
        uvi = 5.0,
        rain = null
    )

    DailyForecastItem(daily = mockDaily)
}
