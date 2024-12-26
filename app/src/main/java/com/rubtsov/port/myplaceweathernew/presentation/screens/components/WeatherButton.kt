package com.rubtsov.port.myplaceweathernew.presentation.screens.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rubtsov.port.myplaceweathernew.R
import com.rubtsov.port.myplaceweathernew.presentation.theme.PrimaryColorLight
import com.rubtsov.port.myplaceweathernew.presentation.theme.TrackColorLight

@Composable
fun WeatherButton(
    onClick: () -> Unit,
    leftIconId: Int,
    buttonText: String,
    rightIconId: Int,
    primaryColor: Color,
    buttonFontColor: Color,
    outlineColor: Color,
    fontFamily: FontFamily,
    cornerRadius: Int,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .height(56.dp)
            .border(
                BorderStroke(1.dp, outlineColor),
                shape = RoundedCornerShape(cornerRadius)
            ),
        shape = RoundedCornerShape(cornerRadius),
        colors = androidx.compose.material3.ButtonDefaults.buttonColors(
            containerColor = primaryColor
        ),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(id = leftIconId),
                contentDescription = null,
                modifier = Modifier.size(40.dp)
            )

            Text(
                text = buttonText,
                color = buttonFontColor,
                fontSize = 16.sp,
                fontFamily = fontFamily,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(start = 12.dp)
            )

            Spacer(modifier = Modifier.weight(1f))

            Image(
                painter = painterResource(id = rightIconId),
                contentDescription = null,
                colorFilter = ColorFilter.tint(buttonFontColor),
                modifier = Modifier
                    .size(24.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CustomButtonPreview() {
    val fontFamily = FontFamily(Font(R.font.inter_regular))

    WeatherButton(
        onClick = { /* Preview Click Action */ },
        leftIconId = R.drawable.left_icon,
        buttonText = "Detail Weather",
        rightIconId = R.drawable.right_icon,
        primaryColor = PrimaryColorLight,
        buttonFontColor = Color.White,
        outlineColor = TrackColorLight,
        fontFamily = fontFamily,
        cornerRadius = 16
    )
}