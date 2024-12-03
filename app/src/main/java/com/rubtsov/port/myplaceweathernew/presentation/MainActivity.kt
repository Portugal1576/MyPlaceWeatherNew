package com.rubtsov.port.myplaceweathernew.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.Modifier
import com.rubtsov.port.myplaceweathernew.presentation.navigation.AppNavigationRoot
import com.rubtsov.port.myplaceweathernew.presentation.theme.WeatherTheme
import dagger.hilt.android.AndroidEntryPoint

// base url - https://api.openweathermap.org/data/3.0/onecall?lat=33.44&lon=-94.04&appid={KEY}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherTheme {
                AppNavigationRoot(modifier = Modifier)
            }
        }
    }
}
