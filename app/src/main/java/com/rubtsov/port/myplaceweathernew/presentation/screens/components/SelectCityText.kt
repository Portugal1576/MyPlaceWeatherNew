package com.rubtsov.port.myplaceweathernew.presentation.screens.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

@Composable
fun SelectCityText() {
    Column {
        Text(
            text = "Виберіть місце на мапі",
            style = TextStyle(
                fontSize = 16.sp,
                color = Color.White
            )
        )
        Text(
            text = "та затисніть на 2 сек.",
            style = TextStyle(
                fontSize = 16.sp,
                color = Color.White
            )
        )
    }
}