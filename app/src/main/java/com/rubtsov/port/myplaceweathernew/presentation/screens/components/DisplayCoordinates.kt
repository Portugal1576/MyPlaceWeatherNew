package com.rubtsov.port.myplaceweathernew.presentation.screens.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import com.rubtsov.port.myplaceweathernew.domain.convertToDMS

@Composable
fun DisplayCoordinates(lat: Double, lng: Double, color: Color, fontSize: TextUnit) {
    val (latDMS, lngDMS) = convertToDMS(lat, lng)
    val locationString = "$latDMS    $lngDMS"

    Text(
        text = "$locationString",
        style = TextStyle(
            fontSize = fontSize,
            color = color,
            fontWeight = FontWeight.Bold
        ),
        fontStyle = FontStyle.Italic
    )
}