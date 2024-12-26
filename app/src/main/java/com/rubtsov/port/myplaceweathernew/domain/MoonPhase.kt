package com.rubtsov.port.myplaceweathernew.domain

fun getMoonPhaseText(phase: Double): String {
    return when {
        phase < 0.02 || phase > 0.98 -> "New Moon"
        phase < 0.25 -> "Waxing Crescent"
        phase < 0.27 -> "First Quarter"
        phase < 0.48 -> "Waxing Gibbous"
        phase < 0.52 -> "Full Moon"
        phase < 0.73 -> "Waning Gibbous"
        phase < 0.75 -> "Last Quarter"
        phase < 0.98 -> "Waning Crescent"
        else -> "New Moon"
    }
}