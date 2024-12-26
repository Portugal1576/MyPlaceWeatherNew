package com.rubtsov.port.myplaceweathernew.presentation.screens.detail

import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.rubtsov.port.myplaceweathernew.presentation.screens.MainViewModel
import com.rubtsov.port.myplaceweathernew.presentation.screens.components.DisplayCoordinates
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailScreenRoot(
    navController: NavController
) {
    val context = LocalContext.current
    val activity = context as? Activity

    DetailScreen(
        navigate = { route ->
            navController.navigate(route)
        },
        finishActivity = { activity?.finish() }
    )
}

@Composable
fun DetailScreen(
    navigate: (route: String) -> Unit,
    finishActivity: () -> Unit
) {
    val viewModel: MainViewModel = koinViewModel()
//    val weatherViewModel: WeatherViewModel = koinViewModel()
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(30.dp))
            DisplayCoordinates(viewModel.lat, viewModel.lng, color = Color.Black, 16.sp)
//        WeatherContent(weather = weether)
        }
    }
}