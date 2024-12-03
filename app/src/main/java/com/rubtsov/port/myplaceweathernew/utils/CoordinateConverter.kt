package com.rubtsov.port.myplaceweathernew.utils

import kotlin.math.abs

fun convertToDMS(lat: Double, lng: Double): Pair<String, String> {
    fun decimalToDMS(coordinate: Double): String {
        val degrees = coordinate.toInt()
        val minutesFull = abs((coordinate - degrees) * 60)
        val minutes = minutesFull.toInt()
        val seconds = ((minutesFull - minutes) * 60).toInt()

        return "${abs(degrees)}°${minutes}'${seconds}\""
    }

    val latDMS = decimalToDMS(lat) + if (lat >= 0) "N" else "S"
    val lngDMS = decimalToDMS(lng) + if (lng >= 0) "E" else "W"

    return Pair(latDMS, lngDMS)
}