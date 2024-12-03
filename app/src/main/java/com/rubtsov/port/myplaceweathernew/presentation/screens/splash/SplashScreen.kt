package com.rubtsov.port.myplaceweathernew.presentation.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.rubtsov.port.myplaceweathernew.R
import com.rubtsov.port.myplaceweathernew.presentation.navigation.Screen
import com.rubtsov.port.myplaceweathernew.presentation.screens.components.ProgressIndicator
import kotlinx.coroutines.delay

@Composable
fun SplashScreenRoot(
    navController: NavController,
) {
    SplashScreen(
        navigate = { destinationScreen ->
            navController.navigate(destinationScreen)
        }
    )
}

@Composable
fun SplashScreen(
    navigate: (destinationScreen: Screen) -> Unit,
) {
    LaunchedEffect(Unit) {
        delay(5000)
        navigate(Screen.Choice)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.autumn_portrait),
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            ProgressIndicator()
        }
    }
}
