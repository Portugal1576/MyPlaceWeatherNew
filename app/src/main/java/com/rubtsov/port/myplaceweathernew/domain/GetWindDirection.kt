package com.rubtsov.port.myplaceweathernew.domain

fun getWindDirection(degree: Int): String {
    val directions = listOf(
        "N", "NNE", "NE", "ENE",
        "E", "ESE", "SE", "SSE",
        "S", "SSW", "SW", "WSW",
        "W", "WNW", "NW", "NNW"
    )
    val index = ((degree / 22.5) + 0.5).toInt() % 16
    return directions[index]
}