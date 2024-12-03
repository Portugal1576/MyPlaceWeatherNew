package com.rubtsov.port.myplaceweathernew.presentation.screens.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ProgressIndicator() {
    val primaryColor = MaterialTheme.colorScheme.primary

    CircularProgressIndicator(
        modifier = Modifier
            .size(100.dp),
        color = primaryColor,
        strokeWidth = 8.dp
    )
}