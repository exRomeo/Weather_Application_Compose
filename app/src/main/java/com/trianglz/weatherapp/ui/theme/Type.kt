package com.trianglz.weatherapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontSize = 11.sp,
        lineHeight = 13.sp,
        fontWeight = FontWeight(400),
        letterSpacing = 0.07.sp,
        color = Color(0x99EBEBF5),
    ),
    labelMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        fontWeight = FontWeight(400),
        color = Color(0xFFFFFFFF),
        ),
    labelLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontSize = 13.sp,
        lineHeight = 18.sp,
        fontWeight = FontWeight(400),
        color = Color(0xFFFFFFFF),
        ),
    bodySmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontSize = 15.sp,
        lineHeight = 20.sp,
        fontWeight = FontWeight(400),
        color = Color(0xFFFFFFFF),
        ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontSize = 16.sp,
        lineHeight = 21.sp,
        fontWeight = FontWeight(400),
        color = Color(0xFFFFFFFF),
        ),
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontSize = 17.sp,
        lineHeight = 22.sp,
        fontWeight = FontWeight(400),
        color = Color(0xFFFFFFFF),
        ),
    headlineSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontSize = 17.sp,
        lineHeight = 22.sp,
        fontWeight = FontWeight(600),
        color = Color(0xFFFFFFFF),
        ),
    headlineMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontSize = 20.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight(400),
        letterSpacing = 0.38.sp,
        color = Color(0xFFFFFFFF),
        ),
    headlineLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        fontWeight = FontWeight(400),
        letterSpacing = 0.35.sp,
        color = Color(0xFFFFFFFF),
        ),
    titleSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontSize = 28.sp,
        lineHeight = 34.sp,
        fontWeight = FontWeight(400),
        letterSpacing = 0.36.sp,
        color = Color(0xFFFFFFFF),
        ),
    titleMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontSize = 34.sp,
        lineHeight = 41.sp,
        fontWeight = FontWeight(400),
        letterSpacing = 0.37.sp,
        color = Color(0xFFFFFFFF),
        ),
    titleLarge = TextStyle(
        fontSize = 64.sp,
        lineHeight = 41.sp,
        fontWeight = FontWeight(400),
        color = Color(0xFFFFFFFF),
        letterSpacing = 0.37.sp,
    )
)