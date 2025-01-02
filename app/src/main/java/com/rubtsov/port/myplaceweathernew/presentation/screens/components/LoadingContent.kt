package com.rubtsov.port.myplaceweathernew.presentation.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.rubtsov.port.myplaceweathernew.domain.getSeasonImageResource
import com.rubtsov.port.myplaceweathernew.presentation.theme.BlueLight

@Composable
fun LoadingContent() {
    val seasonImage = remember { getSeasonImageResource() }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = seasonImage),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

        CircularProgressIndicator(
            strokeWidth = 8.dp,
            color = Color.Blue,
            trackColor = BlueLight,
            modifier = Modifier.size(80.dp)
        )
    }
}