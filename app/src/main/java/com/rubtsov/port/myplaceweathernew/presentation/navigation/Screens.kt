package com.rubtsov.port.myplaceweathernew.presentation.navigation

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class Screen : Parcelable {
    @Parcelize
    data object Splash : Screen()

    @Parcelize
    data object Choice : Screen()

    @Parcelize
    data object Detail : Screen()

    @Parcelize
    data object History : Screen()
}
