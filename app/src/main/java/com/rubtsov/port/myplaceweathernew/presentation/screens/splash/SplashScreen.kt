package com.rubtsov.port.myplaceweathernew.presentation.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.rubtsov.port.myplaceweathernew.R
import com.rubtsov.port.myplaceweathernew.presentation.navigation.Screen
import com.rubtsov.port.myplaceweathernew.presentation.screens.components.ProgressIndicator
import kotlinx.coroutines.delay

@Composable
fun SplashScreenRoot(
    navController: NavHostController
) {
    SplashScreen(
        navigate = { destinationScreen ->
            navController.navigate(destinationScreen)
        }
    )
}

@Composable
fun SplashScreen(
    navigate: (destinationScreen: Screen) -> Unit
) {
    LaunchedEffect(Unit) {
        delay(2000)
        navigate(Screen.Choice)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.systemBars)
            .padding(vertical = 8.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.autumn_portrait),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            ProgressIndicator()
        }
    }
}
