package com.rubtsov.port.myplaceweathernew.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.Modifier
import com.rubtsov.port.myplaceweathernew.presentation.navigation.AppNavigationRoot
import com.rubtsov.port.myplaceweathernew.presentation.theme.WeatherTheme

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
