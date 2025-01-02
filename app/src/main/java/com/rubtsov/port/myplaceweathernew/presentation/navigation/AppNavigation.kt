package com.rubtsov.port.myplaceweathernew.presentation.navigation

import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.rubtsov.port.myplaceweathernew.model.Coordinates
import com.rubtsov.port.myplaceweathernew.presentation.screens.choice.ChoiceScreenRoot
import com.rubtsov.port.myplaceweathernew.presentation.screens.detail.DetailScreenRoot
import com.rubtsov.port.myplaceweathernew.presentation.screens.history.HistoryScreenRoot
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.reflect.typeOf

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
        startDestination = Screen.Choice
    ) {
        // Choice Screen
        composable<Screen.Choice> {
            ChoiceScreenRoot(navController = navController)
        }

        // Detail Screen
        composable<Screen.Detail>(
            typeMap = mapOf(
                typeOf<Screen.Detail>() to parcelableType<Screen.Detail>(),
                // Дані, які потрібно передавати
                typeOf<Coordinates>() to parcelableType<Coordinates>()
            )
        ) { backStackEntry ->
            val detail = backStackEntry.toRoute<Screen.Detail>()
            DetailScreenRoot(navController, detail.coordinates)
        }

        // History Screen
        composable<Screen.History> {
            HistoryScreenRoot(navController = navController)
        }
    }
}

// Функція, з допомогою якої можна передати будь-які об'єкти між екранами
inline fun <reified T : Parcelable> parcelableType(
    isNullableAllowed: Boolean = false,
    json: Json = Json,
) = object : NavType<T>(isNullableAllowed = isNullableAllowed) {
    override fun get(bundle: Bundle, key: String) =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            bundle.getParcelable(key, T::class.java)
        } else {
            @Suppress("DEPRECATION")
            bundle.getParcelable(key)
        }

    override fun parseValue(value: String): T = json.decodeFromString(value)

    override fun serializeAsValue(value: T): String = json.encodeToString(value)

    override fun put(bundle: Bundle, key: String, value: T) = bundle.putParcelable(key, value)
}
