package com.rubtsov.port.myplaceweathernew.presentation.screens.choice

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.rubtsov.port.myplaceweathernew.presentation.navigation.Screen
import com.rubtsov.port.myplaceweathernew.presentation.screens.components.MapComponent

@Composable
fun ChoiceScreenRoot(
    navController: NavController,
) {
    val context = LocalContext.current
    val activity = context as? Activity
//
//    val isOnline by isInternetAvailableState()

    ChoiceScreen(
//        isOnline = isOnline,
//        state = state,
//        events = viewModel.eventsFlow,
//        onAction = viewModel::onAction,
        navigate = { destinationScreen ->
            navController.navigate(destinationScreen)
        },
        finishActivity = {
            activity?.finish()
        }
    )
}

@Composable
fun ChoiceScreen(
//    isOnline: Boolean,
//    state: StartScreenState,
//    events: Flow<StartScreenEvent>,
//    onAction: (StartScreenAction) -> Unit,
    navigate: (destinationScreen: Screen) -> Unit,
    finishActivity: () -> Unit
) {
    BackHandler {
        finishActivity()
    }
    MapComponent()
}