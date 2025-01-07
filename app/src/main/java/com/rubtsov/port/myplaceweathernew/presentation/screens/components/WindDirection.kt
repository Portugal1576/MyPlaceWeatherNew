package com.rubtsov.port.myplaceweathernew.presentation.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.rubtsov.port.myplaceweathernew.R

@Composable
fun WindDirection(degree: Int, modifier: Modifier = Modifier, size: Int) {

    val rotationAngle = remember(degree) { degree.toFloat() }
    val sizeImg = size / 2

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {

        Image(
            painter = painterResource(R.drawable.wind_degri_circle),
            contentDescription = "",
            modifier = Modifier
                .size(size.dp),
            contentScale = ContentScale.Crop
        )

        Image(
            painter = painterResource(R.drawable.wind_arrow),
            contentDescription = "",
            modifier = Modifier
                .size(sizeImg.dp)
                .rotate(317f + rotationAngle),
            contentScale = ContentScale.Crop
        )
    }
}