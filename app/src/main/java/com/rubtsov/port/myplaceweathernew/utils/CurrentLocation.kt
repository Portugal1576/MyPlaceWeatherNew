package com.rubtsov.port.myplaceweathernew.utils

import android.annotation.SuppressLint
import android.content.Context
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.Task
import android.location.Location

@SuppressLint("MissingPermission")
fun getCurrentLocation(context: Context, onLocationReceived: (Double, Double) -> Unit) {
    val fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    val locationTask: Task<Location> = fusedLocationClient.lastLocation
    locationTask.addOnSuccessListener { location ->
        location?.let {
            val latitude = it.latitude
            val longitude = it.longitude
            onLocationReceived(latitude, longitude)
        }
    }
    locationTask.addOnFailureListener {
        // Обробка помилки отримання геолокації
    }
}