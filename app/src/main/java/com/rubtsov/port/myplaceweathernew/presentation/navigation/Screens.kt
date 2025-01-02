package com.rubtsov.port.myplaceweathernew.presentation.navigation

import android.os.Parcelable
import com.rubtsov.port.myplaceweathernew.model.Coordinates
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

sealed class Screen {
    @Parcelize
    @Serializable
    data object Choice : Screen(), Parcelable

    @Parcelize
    @Serializable
    data class Detail(val coordinates: Coordinates) : Screen(), Parcelable

    @Parcelize
    @Serializable
    data object History : Screen(), Parcelable
}
