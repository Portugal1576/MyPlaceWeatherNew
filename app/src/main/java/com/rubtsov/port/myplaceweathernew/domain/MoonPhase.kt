package com.rubtsov.port.myplaceweathernew.domain

fun getMoonPhaseText(phase: Double): String {
    return when {
        phase < 0.1 -> "Новий Місяць"
        phase < 0.25 -> "Зростаючий Місяць"
        phase < 0.35 -> "Перша чверть"
        phase < 0.48 -> "При зростанні"
        phase < 0.52 -> "Повний Місяць"
        phase < 0.65 -> "Після повні"
        phase < 0.75 -> "Остання чверть"
        phase < 0.9 -> "Старіючий Місяць"
        else -> "Новий Місяць"
    }
}