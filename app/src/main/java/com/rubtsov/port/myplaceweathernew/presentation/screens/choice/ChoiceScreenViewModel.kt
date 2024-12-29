package com.rubtsov.port.myplaceweathernew.presentation.screens.choice

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ChoiceScreenViewModel : ViewModel() {
    var lat by mutableDoubleStateOf(41.141802)
        private set

    var lng by mutableDoubleStateOf(-7.884147)
        private set

    fun updateLocation(newLat: Double, newLng: Double) {
        lat = newLat
        lng = newLng
    }
}