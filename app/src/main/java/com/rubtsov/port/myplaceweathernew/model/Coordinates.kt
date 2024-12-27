package com.rubtsov.port.myplaceweathernew.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Coordinates(
    val lat: Double,
    val lon: Double
) : Parcelable
