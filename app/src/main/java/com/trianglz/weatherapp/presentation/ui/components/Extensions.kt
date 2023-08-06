package com.trianglz.weatherapp.presentation.ui.components

import androidx.compose.foundation.layout.heightIn
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp

fun Modifier.maxHeightScreenRatio(ratio: Float) = composed {
    this.heightIn(max = (LocalConfiguration.current.screenHeightDp * ratio).dp)
}