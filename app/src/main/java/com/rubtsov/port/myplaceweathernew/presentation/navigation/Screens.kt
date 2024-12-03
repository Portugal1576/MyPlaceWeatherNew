package com.rubtsov.port.myplaceweathernew.presentation.navigation

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable


    sealed class Screen {
        @Parcelize
        @Serializable
        data object Splash : Screen(), Parcelable

        @Parcelize
        @Serializable
        data object Choice : Screen(), Parcelable
//
//        @Parcelize
//        @Serializable
//        data object PassportBooklet : Screen(), Parcelable
//
//        @Parcelize
//        @Serializable
//        data object PassportCard : Screen(), Parcelable
    }
