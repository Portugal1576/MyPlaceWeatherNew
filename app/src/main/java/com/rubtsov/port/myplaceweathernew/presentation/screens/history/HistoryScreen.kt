package com.rubtsov.port.myplaceweathernew.presentation.screens.history

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController

@Composable
fun HistoryScreenRoot(
    navController: NavController,
) {
    val context = LocalContext.current
    val activity = context as? Activity

    HistoryScreen(
        navigate = { route ->
            navController.navigate(route)
        },
        finishActivity = {
            activity?.finish()
        }
    )
}

@Composable
fun HistoryScreen(
    navigate: (route: String) -> Unit,
    finishActivity: () -> Unit
) {
    // Реалізуйте ваш інтерфейс для HistoryScreen тут
}
