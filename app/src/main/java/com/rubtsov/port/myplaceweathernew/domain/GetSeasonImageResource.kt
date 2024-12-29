package com.rubtsov.port.myplaceweathernew.domain

import com.rubtsov.port.myplaceweathernew.R
import java.time.LocalDate

fun getSeasonImageResource(): Int {
    val currentMonth = LocalDate.now().monthValue

    return when (currentMonth) {
        12, 1, 2 -> R.drawable.winter_portrait
        3, 4, 5 -> R.drawable.spring_portrait
        6, 7, 8 -> R.drawable.summer_portrait
        9, 10, 11 -> R.drawable.autumn_portrait
        else -> R.drawable.winter_portrait
    }
}