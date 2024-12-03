package com.rubtsov.port.myplaceweathernew.presentation.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val LightColorPalette = lightColorScheme(
    primary = PrimaryColorLight,
    background = DialogColorLight,
    surface = TrackColorLight,
    onPrimary = PrimaryColorLight
)

val DarkColorPalette = darkColorScheme(
    primary = PrimaryColorDark,
    background = DialogColorDark,
    surface = TrackColorDark,
    onPrimary = PrimaryColorDark
)

val MyTypography = Typography(
    bodyLarge = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),
    headlineLarge = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp,
        lineHeight = 36.sp
    )
)

@Composable
fun WeatherTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val colors: ColorScheme = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colorScheme = colors,
        typography = MyTypography,
        content = content
    )
}
