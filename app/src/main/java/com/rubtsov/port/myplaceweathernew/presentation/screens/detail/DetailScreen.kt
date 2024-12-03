package com.rubtsov.port.myplaceweathernew.presentation.screens.detail

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.rubtsov.port.myplaceweathernew.presentation.navigation.Screen

@Composable
fun DetailScreenRoot(
    navController: NavController,
) {
    val context = LocalContext.current
    val activity = context as? Activity
//
//    val isOnline by isInternetAvailableState()

    DetailScreen(
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
fun DetailScreen(
//    isOnline: Boolean,
//    state: StartScreenState,
//    events: Flow<StartScreenEvent>,
//    onAction: (StartScreenAction) -> Unit,
    navigate: (destinationScreen: Screen) -> Unit,
    finishActivity: () -> Unit
) {

}