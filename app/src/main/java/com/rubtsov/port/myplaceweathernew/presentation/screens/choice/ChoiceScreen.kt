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
import androidx.navigation.NavController
import com.rubtsov.port.myplaceweathernew.R
import com.rubtsov.port.myplaceweathernew.presentation.screens.components.MapComponent
import com.rubtsov.port.myplaceweathernew.presentation.screens.components.WeatherButton
import com.rubtsov.port.myplaceweathernew.presentation.theme.ChoiceButtonColor

@Composable
fun ChoiceScreenRoot(
    navController: NavController,
) {
    val context = LocalContext.current
    val activity = context as? Activity

    ChoiceScreen(
        navigate = { route ->
            navController.navigate(route)
        },
        finishActivity = { activity?.finish() }
    )
}

@Composable
fun ChoiceScreen(
    navigate: (route: String) -> Unit,
    finishActivity: () -> Unit
) {
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
                navigate("Detail")
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
