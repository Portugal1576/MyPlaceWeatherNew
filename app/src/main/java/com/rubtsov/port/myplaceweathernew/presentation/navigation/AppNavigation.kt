package com.rubtsov.port.myplaceweathernew.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rubtsov.port.myplaceweathernew.presentation.screens.choice.ChoiceScreenRoot
import com.rubtsov.port.myplaceweathernew.presentation.screens.detail.DetailScreenRoot
import com.rubtsov.port.myplaceweathernew.presentation.screens.history.HistoryScreenRoot
import com.rubtsov.port.myplaceweathernew.presentation.screens.splash.SplashScreenRoot

@Composable
fun AppNavigationRoot(modifier: Modifier) {
    val navController = rememberNavController()
    AppNavigation(modifier = modifier, navController = navController)
}

@Composable
fun AppNavigation(
    modifier: Modifier,
    navController: NavHostController
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = "Splash"
    ) {
        // Splash Screen
        composable(route = "Splash") {
            SplashScreenRoot(navController = navController)
        }

        // Choice Screen
        composable(route = "Choice") {
            ChoiceScreenRoot(navController = navController)
        }

        // Detail Screen with arguments
        composable(route = "Detail") {
            DetailScreenRoot(navController = navController)
        }

        // History Screen
        composable(route = "History") {
            HistoryScreenRoot(navController = navController)
        }
    }
}
