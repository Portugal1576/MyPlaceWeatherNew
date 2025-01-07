package com.rubtsov.port.myplaceweathernew.presentation.screens.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.rubtsov.port.myplaceweathernew.R
import com.rubtsov.port.myplaceweathernew.model.Hourly
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun WeatherContent(hourly: Hourly, modifier: Modifier = Modifier) {

    val date = remember(hourly.dt) {
        SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault()).format(Date(hourly.dt * 1000L))
    }
    val pressure = hourly.pressure

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(8.dp))

        Card(
            border = BorderStroke(width = 1.dp, Color.Red),
            colors = CardDefaults.cardColors(containerColor = Color.Transparent),
            shape = RoundedCornerShape(size = 8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 6.dp)
        ) {
            Column(
                modifier = Modifier
                    .background(Color.LightGray)
                    .fillMaxWidth()
                    .padding(10.dp)
            )
            {
                Text(
                    text = date,
                    color = Color.Red,
                    fontFamily = FontFamily(
                        Font(R.font.inter_bold, weight = FontWeight.Thin)
                    ),
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier
                        .height(IntrinsicSize.Min)
                ) {

                    Column {
                        Text(
                            text = "${stringResource(R.string.temp)}: ",
                            fontFamily = FontFamily(
                                Font(R.font.inter_bold, weight = FontWeight.Thin)
                            ),
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "${stringResource(R.string.humidity)}: ",
                            fontFamily = FontFamily(
                                Font(R.font.inter_bold, weight = FontWeight.Thin)
                            ),
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "${stringResource(R.string.wind_speed)}: ",
                            fontFamily = FontFamily(
                                Font(R.font.inter_bold, weight = FontWeight.Thin)
                            ),
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "${stringResource(R.string.pressure)}:",
                            fontFamily = FontFamily(
                                Font(R.font.inter_bold, weight = FontWeight.Thin)
                            ),
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.width(65.dp))

                    Column {
                        Spacer(
                            modifier = Modifier
                                .fillMaxHeight()
                                .width(1.dp)
                                .background(Color.Black)
                        )
                    }

                    Spacer(modifier = Modifier.width(25.dp))

                    Column {
                        Text(
                            text = "${hourly.temp}°C",
                            fontFamily = FontFamily(
                                Font(R.font.roboto_thin, weight = FontWeight.Thin)
                            ),
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "${hourly.humidity}%",
                            fontFamily = FontFamily(
                                Font(R.font.roboto_thin, weight = FontWeight.Thin)
                            ),
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "${hourly.wind_speed} м/с",
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
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {

                    Spacer(
                        modifier = Modifier
                            .width(40.dp)
                    )

                    hourly.weather.firstOrNull()?.icon?.let { iconCode ->
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

                    Spacer(
                        modifier = Modifier
                            .width(50.dp)
                    )

                    WindDirection(
                        hourly.wind_deg,
                        Modifier
                            .padding(end = 10.dp)
                            .size(80.dp)
                            .clip(RoundedCornerShape(8.dp)),
                        80
                    )
                }
            }
        }
    }
}
