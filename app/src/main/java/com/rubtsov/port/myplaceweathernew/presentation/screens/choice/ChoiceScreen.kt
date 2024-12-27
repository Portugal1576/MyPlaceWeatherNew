package com.rubtsov.port.myplaceweathernew.presentation.screens.choice

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.rubtsov.port.myplaceweathernew.R
import com.rubtsov.port.myplaceweathernew.model.Coordinates
import com.rubtsov.port.myplaceweathernew.presentation.navigation.Screen
import com.rubtsov.port.myplaceweathernew.presentation.screens.components.MapComponent
import com.rubtsov.port.myplaceweathernew.presentation.screens.components.WeatherButton
import com.rubtsov.port.myplaceweathernew.presentation.theme.ChoiceButtonColor
import org.koin.androidx.compose.koinViewModel

@Composable
fun ChoiceScreenRoot(
    navController: NavHostController,
) {
    val context = LocalContext.current
    val activity = context as? Activity

    ChoiceScreen(
        navigate = { destinationScreen ->
            navController.navigate(destinationScreen)
        },
        finishActivity = { activity?.finish() }
    )
}

@Composable
fun ChoiceScreen(
    navigate: (destinationScreen: Screen) -> Unit,
    finishActivity: () -> Unit
) {
    val viewModel: ChoiceScreenViewModel = koinViewModel()
    val coordinates = Coordinates(viewModel.lat, viewModel.lng)

    BackHandler {
        finishActivity()
    }

    Column(
        Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.navigationBars)
    ) {
        MapComponent(
            modifier = Modifier.weight(1f)
        )

        WeatherButton(
            onClick = {
                navigate(Screen.Detail(coordinates))
            },
            leftIconId = R.drawable.icon_weather,
            buttonText = "Weather Detail",
            rightIconId = R.drawable.right_icon,
            primaryColor = ChoiceButtonColor,
            buttonFontColor = Color.White,
            outlineColor = Color.Black,
            fontFamily = androidx.compose.ui.text.font.FontFamily.Default,
            cornerRadius = 16,
            modifier = Modifier.padding(bottom = 10.dp, start = 16.dp, end = 16.dp)
        )
    }
}
